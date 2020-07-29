package eu.els.sie.firestorm.backend.model.dto;

import java.io.Serializable;

public class JwtAuthenticationResponseDTO  implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
   }