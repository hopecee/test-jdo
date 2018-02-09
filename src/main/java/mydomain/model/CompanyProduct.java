import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.identity.LongIdentity;

/**
 *
 * @author hope
 */
 @PersistenceCapable(identityType = IdentityType.APPLICATION, cacheable = "false", detachable = "true",
        objectIdClass = CompanyProduct.PK.class)
public class CompanyProduct implements Serializable {

    private static final long serialVersionUID = -7578808257727591074L;

    @PrimaryKey
    private Company company; // PK
    @PrimaryKey
    private Product product; // PK

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CompanyProduct{" + "company=" + company + ", product=" + product + '}';
    }

    ///=============================================================
    public static class PK implements Serializable {

        private static final long serialVersionUID = -2090216333556951760L;
        public LongIdentity company; // Use same name as BusinessRelation field
        public LongIdentity product; // Use same name as BusinessRelation field

        public PK() {
        }

        public PK(String s) {
            StringTokenizer st = new StringTokenizer(s, "::");
            this.company = new LongIdentity(Company.class, st.nextToken());//
            this.product = new LongIdentity(Product.class, st.nextToken());//
        }

        @Override
        public String toString() {
            return (company.toString() + "::" + product.toString());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PK other = (PK) obj;
            if (!Objects.equals(this.company, other.company)) {
                return false;
            }
            if (!Objects.equals(this.product, other.product)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 47 * hash + Objects.hashCode(this.company);
            hash = 47 * hash + Objects.hashCode(this.product);
            return hash;
        }

    }

}
