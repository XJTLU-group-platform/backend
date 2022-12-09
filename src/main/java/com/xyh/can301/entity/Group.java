package com.xyh.can301.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Data
public class Group {

    private String gid;

    private String gtag;

    private String gtitle;

    private String gdescription;

    private Integer gnumber;

     private Integer gnownum;

     private String gleaderid;

}
