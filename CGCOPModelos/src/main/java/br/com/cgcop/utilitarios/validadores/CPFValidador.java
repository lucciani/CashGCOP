/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios.validadores;

import br.com.cgcop.utilitarios.CpfCnpjUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ari
 */
@FacesValidator("CPFValidador")
public class CPFValidador implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        if (!CpfCnpjUtil.validaCPF(CpfCnpjUtil.retiraFormatacaoCPF(o.toString()))) {
            FacesMessage msg
                    = new FacesMessage("Este não é um CPF valido!");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }

}
