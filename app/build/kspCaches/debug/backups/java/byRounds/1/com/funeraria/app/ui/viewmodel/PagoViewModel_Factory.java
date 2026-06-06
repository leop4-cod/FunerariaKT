package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.PagoRepository;
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
public final class PagoViewModel_Factory implements Factory<PagoViewModel> {
  private final Provider<PagoRepository> repoProvider;

  public PagoViewModel_Factory(Provider<PagoRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public PagoViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static PagoViewModel_Factory create(Provider<PagoRepository> repoProvider) {
    return new PagoViewModel_Factory(repoProvider);
  }

  public static PagoViewModel newInstance(PagoRepository repo) {
    return new PagoViewModel(repo);
  }
}
