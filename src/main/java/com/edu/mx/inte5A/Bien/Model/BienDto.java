package com.edu.mx.inte5A.Bien.Model;

import com.edu.mx.inte5A.Lugar.Model.Lugar;
import com.edu.mx.inte5A.Lugar.Model.LugarDto;
import com.edu.mx.inte5A.Marca.Model.Marca;
import com.edu.mx.inte5A.Marca.Model.MarcaDto;
import com.edu.mx.inte5A.Modelo.Model.Modelo;
import com.edu.mx.inte5A.Modelo.Model.ModeloDto;
import com.edu.mx.inte5A.TipoBien.Model.TipoBien;
import com.edu.mx.inte5A.TipoBien.Model.TipoBienDto;
import com.edu.mx.inte5A.Usuario.Model.Usuario;
import com.edu.mx.inte5A.Usuario.Model.UsuarioDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BienDto {

    @NotNull(groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del bien")
    private Long idBien;

    @NotBlank(groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el codigo de barras")
    private String codigoBarras;

    @NotBlank (groups = {RegistrarBien.class, ModificarBien.class}, message = "Es necesario el numero de serie")
    private String nSerie;

    private boolean status;

    // Detalles de entidades relacionadas
    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del tipo de bien")
    private TipoBienDto tipoBienDto;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del responsable")
    private UsuarioDto usuarioDto;

    @NotNull(groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del modelo")
    private ModeloDto modeloDto;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id de la marca")
    private MarcaDto marcaDto;

    @NotNull (groups = {ModificarBien.class, CambiarStatus.class}, message = "Es necesario el id del lugar")
    private LugarDto lugarDto;

    public BienDto() {}

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNSerie() {
        return nSerie;
    }

    public void setNSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public TipoBienDto getTipoBienDto() {
        return tipoBienDto;
    }

    public void setTipoBienDto(TipoBienDto tipoBienDto) {
        this.tipoBienDto = tipoBienDto;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public ModeloDto getModeloDto() {
        return modeloDto;
    }

    public void setModeloDto(ModeloDto modeloDto) {
        this.modeloDto = modeloDto;
    }

    public MarcaDto getMarcaDto() {
        return marcaDto;
    }

    public void setMarcaDto(MarcaDto marcaDto) {
        this.marcaDto = marcaDto;
    }

    public LugarDto getLugarDto() {
        return lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    public BienDto getBienDto() {
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Validaciones
    public interface RegistrarBien {}
    public interface ModificarBien {}
    public interface CambiarStatus {}

}
