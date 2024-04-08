package cc.sika.sikaloginserver.dto;

import lombok.Data;

/**
 * captcha render entity
 * contain code key and base64 with type information
 * base64 attribute can be put into src attribute of img tar
 */
@Data
public class CaptchaEntity {
    private String codeKey;
    private String base64;
}
