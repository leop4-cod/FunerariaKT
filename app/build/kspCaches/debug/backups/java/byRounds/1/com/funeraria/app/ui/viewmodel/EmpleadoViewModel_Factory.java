package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.EmpleadoRepository;
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
public final class EmpleadoViewModel_Factory implements Factory<EmpleadoViewModel> {
  private final Provider<EmpleadoRepository> repoProvider;

  public EmpleadoViewModel_Factory(Provider<EmpleadoRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public EmpleadoViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static EmpleadoViewModel_Factory create(Provider<EmpleadoRepository> repoProvider) {
    return new EmpleadoViewModel_Factory(repoProvider);
  }

  public static EmpleadoViewModel newInstance(EmpleadoRepository repo) {
    return new EmpleadoViewModel(repo);
  }
}
