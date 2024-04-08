package cc.sika.sikaloginserver.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class R<T> {
    private T value;
    private String message;
    private int code;

    public static <T> R<T> success(T value) {
        return new R<>(value, "success", 200);
    }

    public static <T> R<T> fail(T value, String message) {
        return new R<>(value, message, 500);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<>(null, message, code);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(null, message, 500);
    }

    public static <T> R<T> success(int code, String message) {
        return new R<>(null, "success", 200);
    }
}
