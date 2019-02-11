/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.innova4j.api.commons.domain.BaseDomain;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_role")
public class AuthRole extends BaseDomain {

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

	@NotNull
	@Type(type = "json")
	@Column(name = "name", columnDefinition = "json")
	private Map<String, String> name;

	@NotNull
	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description;

	@Column(name = "parent_id")
	private String parentId;

	@Column(name = "view")
	private String view;

	@Column(name = "create")
	private String create;

	@Column(name = "edit")
	private String edit;

	@Column(name = "remove")
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

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	public Map<String, String> getDescription() {
		return description;
	}

	public void setDescription(Map<String, String> description) {
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

	@Override
	public String toString() {
		return "AuthRole [id=" + id + ", name=" + name + ", description=" + description + ", parentId=" + parentId
				+ ", view=" + view + ", create=" + create + ", edit=" + edit + ", remove=" + remove + ", created="
				+ created + ", lastModified=" + lastModified + "]";
	}

}
