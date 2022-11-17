package com.xyh.can301.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Data
public class Group {

    private Integer gid;

    private String gtag;

    private String gtitle;

    private String gdescription;

    private Integer gnumber;

     private Integer gnownum;

     private Integer gleaderid;

}
