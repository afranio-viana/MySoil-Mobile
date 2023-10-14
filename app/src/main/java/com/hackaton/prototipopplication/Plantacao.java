package com.hackaton.prototipopplication;

import android.widget.EditText;

public class Plantacao {
    private String areaFazenda;
    private String desejaPlantar;
    private String rotacaoPlantio;
    private String manejoResiduos;
    private String plantioColheita;
    private String irrigacaoFertilizacao;

    public String getAreaFazenda() {
        return areaFazenda;
    }

    public void setAreaFazenda(String areaFazenda) {
        this.areaFazenda = areaFazenda;
    }

    public String getDesejaPlantar() {
        return desejaPlantar;
    }

    public void setDesejaPlantar(String desejaPlantar) {
        this.desejaPlantar = desejaPlantar;
    }

    public String getRotacaoPlantio() {
        return rotacaoPlantio;
    }

    public void setRotacaoPlantio(String rotacaoPlantio) {
        this.rotacaoPlantio = rotacaoPlantio;
    }

    public String getManejoResiduos() {
        return manejoResiduos;
    }

    public void setManejoResiduos(String manejoResiduos) {
        this.manejoResiduos = manejoResiduos;
    }

    public String getPlantioColheita() {
        return plantioColheita;
    }

    public void setPlantioColheita(String plantioColheita) {
        this.plantioColheita = plantioColheita;
    }

    public String getIrrigacaoFertilizacao() {
        return irrigacaoFertilizacao;
    }

    public void setIrrigacaoFertilizacao(String irrigacaoFertilizacao) {
        this.irrigacaoFertilizacao = irrigacaoFertilizacao;
    }
}
