package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.DifuntoRepository;
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
public final class DifuntoViewModel_Factory implements Factory<DifuntoViewModel> {
  private final Provider<DifuntoRepository> repoProvider;

  public DifuntoViewModel_Factory(Provider<DifuntoRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public DifuntoViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static DifuntoViewModel_Factory create(Provider<DifuntoRepository> repoProvider) {
    return new DifuntoViewModel_Factory(repoProvider);
  }

  public static DifuntoViewModel newInstance(DifuntoRepository repo) {
    return new DifuntoViewModel(repo);
  }
}
