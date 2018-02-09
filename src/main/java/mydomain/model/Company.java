
package mydomain.model;
import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION, cacheable = "false", detachable = "true")
public class Company implements Serializable {

    private static final long serialVersionUID = 8269335445554701874L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    long id;
    @Persistent
    private String name = null;
    
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + '}';
    }
    
}
