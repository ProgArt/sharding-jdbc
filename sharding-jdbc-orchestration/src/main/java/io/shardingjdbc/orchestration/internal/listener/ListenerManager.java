/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.orchestration.internal.listener;

import io.shardingjdbc.core.jdbc.core.datasource.MasterSlaveDataSource;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import io.shardingjdbc.orchestration.api.config.OrchestrationConfiguration;
import io.shardingjdbc.orchestration.internal.config.ConfigurationListenerManager;
import io.shardingjdbc.orchestration.internal.state.datasource.DataSourceListenerManager;
import io.shardingjdbc.orchestration.internal.state.instance.InstanceListenerManager;

/**
 * Registry center's listener manager.
 *
 * @author caohao
 */
public final class ListenerManager {
    
    private final ConfigurationListenerManager configurationListenerManager;
    
    private final InstanceListenerManager instanceListenerManager;
    
    private final DataSourceListenerManager dataSourceListenerManager;
    
    public ListenerManager(final OrchestrationConfiguration config) {
        configurationListenerManager = new ConfigurationListenerManager(config);
        instanceListenerManager = new InstanceListenerManager(config);
        dataSourceListenerManager = new DataSourceListenerManager(config);
    }
    
    /**
     * Initialize listeners for sharding data source.
     * 
     * @param shardingDataSource sharding data source
     */
    public void initShardingListeners(final ShardingDataSource shardingDataSource) {
        configurationListenerManager.addShardingDataSourceChangedListener(shardingDataSource);
        instanceListenerManager.addShardingDataSourceChangedListener(shardingDataSource);
        dataSourceListenerManager.addShardingDataSourceChangedListener(shardingDataSource);
    }
    
    /**
     * Initialize listeners for master-slave data source.
     *
     * @param masterSlaveDataSource master-slave data source
     */
    public void initMasterSlaveListeners(final MasterSlaveDataSource masterSlaveDataSource) {
        configurationListenerManager.addMasterSlaveDataSourceChangedListener(masterSlaveDataSource);
        instanceListenerManager.addMasterSlaveDataSourceChangedListener(masterSlaveDataSource);
        dataSourceListenerManager.addMasterSlaveDataSourceChangedListener(masterSlaveDataSource);
    }
}