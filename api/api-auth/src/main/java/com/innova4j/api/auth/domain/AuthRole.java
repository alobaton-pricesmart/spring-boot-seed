/**
 * 
 */
package com.innova4j.api.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.innova4j.api.commons.domain.BaseDomain;

/**
 * @author innova4j-team
 *
 */
@Entity
@Table(name = "auth_role")
public class AuthRole extends BaseDomain {

	@Id
	@NotNull
	private String id;
	private String description;
	private String parentId;
	private String view;
	private String create;
	private String edit;
	private String remove;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the view
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return the create
	 */
	public String getCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public void setCreate(String create) {
		this.create = create;
	}

	/**
	 * @return the edit
	 */
	public String getEdit() {
		return edit;
	}

	/**
	 * @param edit the edit to set
	 */
	public void setEdit(String edit) {
		this.edit = edit;
	}

	/**
	 * @return the remove
	 */
	public String getRemove() {
		return remove;
	}

	/**
	 * @param remove the remove to set
	 */
	public void setRemove(String remove) {
		this.remove = remove;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthRole [id=" + id + ", description=" + description + ", parentId=" + parentId + ", view=" + view
				+ ", create=" + create + ", edit=" + edit + ", remove=" + remove + ", created=" + created
				+ ", lastModified=" + lastModified + "]";
	}

}
