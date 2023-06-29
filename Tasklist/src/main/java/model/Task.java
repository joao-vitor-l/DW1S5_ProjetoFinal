package model;

import java.util.Date;

public class Task{
	private int id;
    private String title;
    private String description;
    private Date dtCreation;
    private Date dtConclusion;
    private String status;
    private int idUser;
    
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Date getDtCreation(){
		return dtCreation;
	}
	public void setDtCreation(Date dtCreation){
		this.dtCreation = dtCreation;
	}
	public Date getDtConclusion(){
		return dtConclusion;
	}
	public void setDtConclusion(Date dtConclusion){
		this.dtConclusion = dtConclusion;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public int getIdUser(){
		return idUser;
	}
	public void setIdUser(int idUser){
		this.idUser = idUser;
	}
}
