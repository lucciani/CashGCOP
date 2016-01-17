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
@FacesValidator("CNPJValidador")
public class CNPJValidador implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        if (!CpfCnpjUtil.validaCNPJ(CpfCnpjUtil.retiraFormatacaoCNPJ(o.toString()))) {
            FacesMessage msg
                    = new FacesMessage("Este não é um CNPJ valido!");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }
}
