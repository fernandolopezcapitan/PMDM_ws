package com.dam.salesianostriana.pmdm.killduck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Usuario {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("points")
    @Expose
    private String points;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     * The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     * The points
     */
    public String getPoints() {
        return points;
    }

    /**
     *
     * @param points
     * The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

}