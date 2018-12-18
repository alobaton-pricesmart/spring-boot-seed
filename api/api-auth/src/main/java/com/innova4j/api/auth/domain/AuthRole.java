/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.innova4j.api.commons.domain.BaseDomain;
import com.innova4j.api.message.MessageConstants;

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
	@NotNull
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = MessageConstants.LOCALE)
	private Map<String, String> name;
	@NotNull
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = MessageConstants.LOCALE)
	private Map<String, String> description;
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
