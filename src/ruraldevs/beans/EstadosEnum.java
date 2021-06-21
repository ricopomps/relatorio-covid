package ruraldevs.beans;

public enum EstadosEnum {
    AC("Acre"), AL("Alagoas"), AP("Amapá"), AM("Amazonas"), BA("Bahia"), CE("Ceará"), ES("Espírito Santo"), GO("Goiás"), MA("Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas Gerais"), PA("Pará"), PB("Paraíba"), PR("Paraná"), PE(
            "Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), RS("Rio Grande do Sul"), RO("Rondônia"), RR("Roraima"), SC("Santa Catarina"), SP("São Paulo"), SE("Sergipe"), TO("Tocantins"), DF("Distrito Federal");

    private String nomeEstado;

    EstadosEnum(String nomeEstado) {
        setNomeEstado(nomeEstado);
    }

    /**
     * @return String return the nomeEstado
     */
    public String getNomeEstado() {
        return nomeEstado;
    }

    /**
     * @param nomeEstado the nomeEstado to set
     */
    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

}
