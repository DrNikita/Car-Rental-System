package by.epam.lab.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	public Entity() {

	}

	public Entity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}
}
