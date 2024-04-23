package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PayInfoEntityKey implements Serializable {
    private String orderId;
    private String mode;
}
