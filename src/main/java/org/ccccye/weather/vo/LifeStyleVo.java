package org.ccccye.weather.vo;

/**
 * 生活指数
 */
public class LifeStyleVo {
    /**
     * 舒适度
     */
    private LifeStyleItemVo comf;

    /**
     * 洗车指数
     */
    private LifeStyleItemVo cw;

    /**
     * 穿衣指数
     */
    private LifeStyleItemVo drsg;

    /**
     * 感冒指数
     */
    private LifeStyleItemVo flu;

    /**
     * 运动指数
     */
    private LifeStyleItemVo sport;

    /**
     * 紫外线指数
     */
    private LifeStyleItemVo uv;

    public LifeStyleItemVo getComf() {
        return comf;
    }

    public void setComf(LifeStyleItemVo comf) {
        this.comf = comf;
    }

    public LifeStyleItemVo getCw() {
        return cw;
    }

    public void setCw(LifeStyleItemVo cw) {
        this.cw = cw;
    }

    public LifeStyleItemVo getDrsg() {
        return drsg;
    }

    public void setDrsg(LifeStyleItemVo drsg) {
        this.drsg = drsg;
    }

    public LifeStyleItemVo getFlu() {
        return flu;
    }

    public void setFlu(LifeStyleItemVo flu) {
        this.flu = flu;
    }

    public LifeStyleItemVo getSport() {
        return sport;
    }

    public void setSport(LifeStyleItemVo sport) {
        this.sport = sport;
    }

    public LifeStyleItemVo getUv() {
        return uv;
    }

    public void setUv(LifeStyleItemVo uv) {
        this.uv = uv;
    }
}
