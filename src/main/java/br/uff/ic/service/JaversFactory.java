package br.uff.ic.service;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by guilherme on 20/05/17.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public final class JaversFactory {

    private Javers INSTANCE;

    private JaversFactory() {
        this.INSTANCE = JaversBuilder.javers().build();
    }

    public Javers getInstance(){
        return INSTANCE != null ? INSTANCE : JaversBuilder.javers().build();
    }
}
