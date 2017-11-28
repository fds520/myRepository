package com.my.space.domain;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private ID id;

    public BaseEntity() {
    }

    public boolean equals(Object obj) {
        if(null == obj) {
            return false;
        } else if(this == obj) {
            return true;
        } else if(!this.getClass().equals(obj.getClass())) {
            return false;
        } else {
            BaseEntity<?> that = (BaseEntity)obj;
            return null == this.getId()?false:this.getId().equals(that.getId());
        }
    }

    public int hashCode() {
        int hashCode = 17;
        hashCode = hashCode + (null == this.getId()?0:this.getId().hashCode() * 31);
        return hashCode;
    }

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
