package com.isaproject.isaproject.Model.Medicine;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Users.Supplier;
import javax.persistence.*;

@Entity
@DiscriminatorValue("medications_for_supplier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SupplierMedications {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "quantity", nullable = true)
    private int quantity;

    @Column(name = "reservedQuantity", nullable = true)
    private int reservedQuantity;

    @Column(name = "name", nullable = true)
    private String name;


    @Column(name = "code", unique=true, nullable = true)
    private long code;


    @Column(name = "form", nullable = true)
    private String form;


    @Column(name = "type", nullable = true)
    private String type;

    @JsonBackReference(value="medications-supplier")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = true, unique = false)
    private Supplier supplier = new Supplier();


    public SupplierMedications() {
    }

    public SupplierMedications(Integer id, int quantity, int reservedQuantity, String name, long code, String form, String type, Supplier supplier) {
        this.id = id;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.supplier = supplier;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
