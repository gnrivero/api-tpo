package model;

import java.util.Date;

import dao.AuditoriaReclamoDAO;
import dao.DAOhelper;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.reclamo.Reclamo;
import view.AuditoriaReclamoView;

public class AuditoriaReclamo {
	
	private Integer idAuditoria;
	private Reclamo reclamo;
	private String datoAnterior;
	private String datoNuevo;
	private Usuario usuario;
	private Date fecha;
	
	
	public AuditoriaReclamo(String datoAnterior, String datoNuevo, Usuario usuario, Date fecha){
		this.datoAnterior = datoAnterior;
		this.datoNuevo = datoNuevo;
		this.usuario = usuario;
		this.fecha = fecha;		
	}
	
	public AuditoriaReclamo(Reclamo reclamo, String datoAnterior, String datoNuevo, Usuario usuario){		
		this.reclamo = reclamo;
		this.datoAnterior = datoAnterior;
		this.datoNuevo = datoNuevo;
		this.usuario = usuario;
	}
	
	public void guardar() throws ConexionException, AccesoException{		
		AuditoriaReclamoDAO.getInstance().crearAuditoria(this);		
	}
	
	public AuditoriaReclamoView toView(){
		return new AuditoriaReclamoView(this.datoAnterior, this.datoNuevo, this.usuario.getUsername(), DAOhelper.getDiaMesAnioHoraDateFormat().format(this.fecha));
	}
	
	public Integer getIdAuditoria() {
		return idAuditoria;
	}
	public void setIdAuditoria(Integer idAuditoria) {
		this.idAuditoria = idAuditoria;
	}
	public Reclamo getReclamo() {
		return reclamo;
	}
	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}
	public String getDatoAnterior() {
		return datoAnterior;
	}
	public void setDatoAnterior(String datoAnterior) {
		this.datoAnterior = datoAnterior;
	}
	public String getDatoNuevo() {
		return datoNuevo;
	}
	public void setDatoNuevo(String datoNuevo) {
		this.datoNuevo = datoNuevo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}