package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.ContratoRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class ContratoViewModel_Factory implements Factory<ContratoViewModel> {
  private final Provider<ContratoRepository> repoProvider;

  public ContratoViewModel_Factory(Provider<ContratoRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ContratoViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static ContratoViewModel_Factory create(Provider<ContratoRepository> repoProvider) {
    return new ContratoViewModel_Factory(repoProvider);
  }

  public static ContratoViewModel newInstance(ContratoRepository repo) {
    return new ContratoViewModel(repo);
  }
}
