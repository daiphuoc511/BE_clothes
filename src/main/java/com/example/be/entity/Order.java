package com.example.be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;

    @NotBlank(message = "Họ tên không được để trống!")
    @Column(name = "customer_name", columnDefinition = "varchar(50)")
    private String customerName;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Số điện thoại không đúng định dạng!")
    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống!")
    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(name = "total_order")
    private Float totalOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;
}
