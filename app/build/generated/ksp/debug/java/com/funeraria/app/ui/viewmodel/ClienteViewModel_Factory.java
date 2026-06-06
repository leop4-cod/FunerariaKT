package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.domain.repository.ClienteRepository;
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
public final class ClienteViewModel_Factory implements Factory<ClienteViewModel> {
  private final Provider<ClienteRepository> repoProvider;

  public ClienteViewModel_Factory(Provider<ClienteRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ClienteViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static ClienteViewModel_Factory create(Provider<ClienteRepository> repoProvider) {
    return new ClienteViewModel_Factory(repoProvider);
  }

  public static ClienteViewModel newInstance(ClienteRepository repo) {
    return new ClienteViewModel(repo);
  }
}
