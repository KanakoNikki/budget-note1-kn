package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
            name = "getAllItems",
            query = "SELECT i FROM Item AS i"
            ),

    @NamedQuery(
            name = "getItemColumns",
            query = "SELECT COUNT(i) FROM Item AS i"
            )
})

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item_id", nullable = false)
    private Integer item_id;

    @Column(name = "item_name", nullable = false)
    private String item_name;

    public Integer getItem_Id() {
        return item_id;
    }

    public void setItem_Id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}