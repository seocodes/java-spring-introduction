package br.com.seocodes.task_manager.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    // source: o objeto de origem, de onde os valores são copiados.
    // target: o objeto de destino, que vai ser atualizado com os valores do source.
    public void copyNonNullProperties(Object source, Object target){

        // copia todas as propriedades com o mesmo nome e tipo, exceto as listadas em ignoreProperties (terceiro parametro)
        // OU SEJA, aquele último parâmetro (getNull) são as propriedades a serem IGNORADAS!!!!
        // por isso, só vai copiar as Null Properties (nome do metodo né kk)
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Pra auxiliar no PUT das tasks - pega todas as propriedades NULAS
    public String[] getNullPropertyNames(Object source) {
        // BeanWrapper = interface que permite  manipular propriedades de um objeto Java (conhecido como bean) de forma padronizada e simplificada
        // BeanWrapperImpl = implementação concreta do BeanWrapper (que é uma interface)
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Gera um array com todas as propriedades do Bean/objeto
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd : pds){
            // Pega o valor da propriedade (pra verificar se é null ou nao)
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        // Cria um array do mesmo tam. que o das propriedades nulas
        String[] result = new String[emptyNames.size()];

        // Converte o conjunto de nomes (emptyNames) pra um Array de String
        return emptyNames.toArray(result);
    }
}
