package com.workshop.postal.service;

import com.workshop.postal.Dtos.*;
import com.workshop.postal.exceptions.BusinessException;
import com.workshop.postal.models.Cliente;
import com.workshop.postal.models.Empleado;
import com.workshop.postal.models.Envio;
import com.workshop.postal.models.Paquete;
import com.workshop.postal.models.enums.EstadoEnvio;
import com.workshop.postal.models.enums.TipoEmpleado;
import com.workshop.postal.models.enums.TipoPaquete;
import com.workshop.postal.repository.ClienteRepository;
import com.workshop.postal.repository.EmpleadoRepository;
import com.workshop.postal.repository.EnvioRepository;
import com.workshop.postal.service.Interfaces.IEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class EnvioService implements IEnvioService {

        private final EnvioRepository envioRepository;
        private final ClienteRepository clienteRepository;
        private final EmpleadoRepository empleadoRepository;
        private final PaqueteService paqueteService;

        @Autowired
        public EnvioService(EnvioRepository envioRepository,
                            ClienteRepository clienteRepository,
                            EmpleadoRepository empleadoRepository,
                            PaqueteService paqueteService) {
            this.envioRepository = envioRepository;
            this.clienteRepository = clienteRepository;
            this.empleadoRepository = empleadoRepository;
            this.paqueteService = paqueteService;
        }

        public List<Envio> getAllEnvios() {
            return envioRepository.findAll();
        }
        @Transactional
        public EnvioRecibidoDto crearEnvio(EnvioDto request) throws BusinessException {

            Cliente remitente = clienteRepository.findByCedula(request.getCedulaCliente());

            if (remitente == null) {
                throw new BusinessException("El cliente con cedula "+ request.getCedulaCliente() +" debe de estar registrado para\n" +
                        "poder enviar un paquete.");
            }

            TipoPaquete tipoPaquete = calcularTipoPaquete(request.getPeso());

            Paquete paquete = new Paquete(request.getPeso(),
                    request.getValorDeclaradoPaquete(),
                    tipoPaquete);

            Paquete paqueteBD = paqueteService.createPaquete(paquete);

            double valorEnvio = calcularValorEnvio(tipoPaquete);

            Envio envio = new Envio(generarNumeroGuia(),
                    remitente,
                    request.getCiudadOrigen(),
                    request.getCiudadDestino(),
                    request.getDireccionDestino(),
                    request.getNombreRecibe(),
                    request.getCelular(),
                    LocalDateTime.now(),
                    EstadoEnvio.RECIBIDO,
                    valorEnvio,
                    paqueteBD);

            Envio envioBD = envioRepository.save(envio);

            return new EnvioRecibidoDto(envioBD.getNumeroGuia(), envioBD.getEstadoEnvio());
        }

    @Transactional
    public UpdatedEstadoEnvioDto actualizarEstadoEnvio(UpdateEstadoEnvioDto updateEstadoEnvioDto) throws BusinessException {

        Empleado empleado = empleadoRepository.findByCedula(updateEstadoEnvioDto.getCedulaEmpleado());

        if (empleado == null) {
            throw new BusinessException("El empleado con cédula " + updateEstadoEnvioDto.getCedulaEmpleado() + " no existe en nuestra compañía.");
        }

        Envio envio = envioRepository.findByNumeroGuia(updateEstadoEnvioDto.getNumeroGuia());

        if (envio == null) {
            throw new BusinessException("No se encontró un envío con el número de guía " + updateEstadoEnvioDto.getNumeroGuia() + ".");
        }

        if (!empleadoPuedeActualizarEstado(empleado)) {
            throw new BusinessException("El empleado no tiene permisos para actualizar el estado del paquete.");
        }

        if (!puedeCambiarseEstado(envio.getEstadoEnvio(), updateEstadoEnvioDto.getEstadoEnvio())) {
            throw new BusinessException("No se puede cambiar el estado de " + envio.getEstadoEnvio() + " a " + updateEstadoEnvioDto.getEstadoEnvio());
        }

        envio.setEstadoEnvio(updateEstadoEnvioDto.getEstadoEnvio());
        envio = envioRepository.save(envio);

        return UpdatedEstadoEnvioDto.builder()
                .numeroGuia(envio.getNumeroGuia())
                .ultimoEstadoEnvio(envio.getEstadoEnvio())
                .build();
    }

    @Transactional(readOnly = true)
    public List<Envio> filtrarEnviosPorEstado(GetEnvioPorEstadoDto getEnvioPorEstadoDto) throws BusinessException {

        Empleado empleado = empleadoRepository.findByCedula(getEnvioPorEstadoDto.getCedulaEmpleado());

        if (empleado == null) {
            throw new BusinessException("El empleado con cédula " + getEnvioPorEstadoDto.getCedulaEmpleado() + " no existe en nuestra compañía.");
        }

        if (!empleado.getTipoEmpleado().equals(TipoEmpleado.REPARTIDOR) &&
                !empleado.getTipoEmpleado().equals(TipoEmpleado.COORDINADOR)) {
            throw new BusinessException("El empleado no tiene permisos para filtrar envíos por estado.");
        }

        return envioRepository.findByEstadoEnvio(getEnvioPorEstadoDto.getEstadoEnvio());
    }

    @Transactional(readOnly = true)
    public Envio obtenerEnvioPorNumeroGuia(String numeroGuia) {

        if(numeroGuia == null || numeroGuia.isEmpty()){
            throw new BusinessException("El número de guía no puede ser nulo o vacío.");
        }

        return envioRepository.findByNumeroGuia(numeroGuia);
    }

    private boolean puedeCambiarseEstado(EstadoEnvio estadoEnvio, EstadoEnvio estadoEnvioNuevo) {
        return switch (estadoEnvio) {
            case RECIBIDO -> estadoEnvioNuevo.equals(EstadoEnvio.EN_RUTA);
            case EN_RUTA -> estadoEnvioNuevo.equals(EstadoEnvio.ENTREGADO);
            default -> false;
        };
    }

    private boolean empleadoPuedeActualizarEstado(Empleado empleado) {
        return empleado.getTipoEmpleado().equals(TipoEmpleado.REPARTIDOR) ||
                empleado.getTipoEmpleado().equals(TipoEmpleado.COORDINADOR);
    }

    public String generarNumeroGuia() {
        Random random = new Random();
        int numero = random.nextInt(900000) + 100000;

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random randomChar = new Random();

        for (int i = 0; i < 6; i++) {
            int index = randomChar.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        return sb.toString() + numero;
    }

    private TipoPaquete calcularTipoPaquete(double peso) {
            if (peso < 2) {
                return TipoPaquete.LIVIANO;
            } else if (peso < 5) {
                return TipoPaquete.MEDIANO;
            } else {
                return TipoPaquete.GRANDE;
            }
        }

        private double calcularValorEnvio(TipoPaquete tipoPaquete) {
            return switch (tipoPaquete) {
                case LIVIANO -> 30000;
                case MEDIANO -> 40000;
                case GRANDE -> 50000;
            };
        }

    }

