package org.example;

public class AuditoriaSpy {
    public Auditoria spy;

    public AuditoriaSpy(Auditoria spy) {
        this.spy = spy;
    }
    public void registrarConsulta(){
        spy.registrarConsulta();
    }
}
