package com.github.xuanyu66.tidbcloudtoolkit.plugin.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@State(name = "DataService",storages = @Storage("plugin.xml"))
public class DataService implements PersistentStateComponent<DataState> {
    private DataState state = new DataState();

    public static DataService getInstance() {
        return ServiceManager.getService(DataService.class);
    }


    @Override
    public @Nullable DataState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull DataState state) {
        this.state = state;
    }

    public List<ClusterWrapper> getGids(){
        return state.getClusterLists();
    }

}
