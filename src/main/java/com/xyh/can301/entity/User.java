package com.xyh.can301.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Data
public class User implements Serializable {

    private String uid;

    private String uname;

    private String uaccount;

    private String upassword;

    private String ugender;

    private String uage;



}
