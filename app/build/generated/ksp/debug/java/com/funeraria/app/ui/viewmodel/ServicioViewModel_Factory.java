package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.ServicioRepository;
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
public final class ServicioViewModel_Factory implements Factory<ServicioViewModel> {
  private final Provider<ServicioRepository> repoProvider;

  public ServicioViewModel_Factory(Provider<ServicioRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ServicioViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static ServicioViewModel_Factory create(Provider<ServicioRepository> repoProvider) {
    return new ServicioViewModel_Factory(repoProvider);
  }

  public static ServicioViewModel newInstance(ServicioRepository repo) {
    return new ServicioViewModel(repo);
  }
}
