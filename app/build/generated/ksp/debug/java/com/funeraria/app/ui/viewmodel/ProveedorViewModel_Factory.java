package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.ProveedorRepository;
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
public final class ProveedorViewModel_Factory implements Factory<ProveedorViewModel> {
  private final Provider<ProveedorRepository> repoProvider;

  public ProveedorViewModel_Factory(Provider<ProveedorRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ProveedorViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static ProveedorViewModel_Factory create(Provider<ProveedorRepository> repoProvider) {
    return new ProveedorViewModel_Factory(repoProvider);
  }

  public static ProveedorViewModel newInstance(ProveedorRepository repo) {
    return new ProveedorViewModel(repo);
  }
}
