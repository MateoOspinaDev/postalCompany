package com.workshop.postal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Cliente extends Usuario{
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Envio> envios = new ArrayList<>();
}
