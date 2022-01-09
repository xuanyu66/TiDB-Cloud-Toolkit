package com.github.xuanyu66.tidbcloudtoolkit.plugin.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitService;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitServiceImpl;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service
@State(name = "DataService", storages = @Storage("plugin.xml"))
public class DataService implements PersistentStateComponent<DataState> {

  private DataState state = new DataState();

  private static volatile DataService dataService = null;

  public static DataService getDataService() {
    if (dataService == null) {
      synchronized (DataState.class) {
        if (dataService == null) {
          dataService = new DataService();
        }
      }
    }
    return dataService;
  }

  public static DataService getInstance() {
    return ServiceManager.getService(DataService.class);
  }

  private TiDBCloudToolkitService toolkitService;

  @Override
  public @Nullable DataState getState() {
    return state;
  }

  @Override
  public void loadState(@NotNull DataState state) {
    this.state = state;
  }

  public List<ClusterWrapper> getGids() {
    return state.getClusterLists();
  }

  public void login(String name, String password) {
    toolkitService = new TiDBCloudToolkitServiceImpl(name, password);
  }

  public boolean createCluster(ClusterVO clusterVO) {
    return toolkitService.createCluster(clusterVO);
  }

  public List<ClusterWrapper> listClusters() {
    try {
      return toolkitService.listCluster();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  public boolean deleteCluster(ClustersSummary clustersSummary) {
    return toolkitService.deleteCluster(clustersSummary);
  }

  public boolean setTrafficFilter(boolean all, ClustersSummary clustersSummary) {
    return toolkitService.setTrafficFilter(all, clustersSummary);
  }
}
