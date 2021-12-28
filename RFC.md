# RFC: TiDB Cloud Toolkit

作者：种松松、施宇航、杨鑫

## 项目介绍

本项目将会通过 Jetbrain 插件的方式实现对 TiDB cloud dev 集群的创建以及信息管理，同时集成 Spring 框架，自动化注入 TiDB 集群配置信息。

## 背景 & 动机

领先的分布式数据库TiDB 正从分布式加速拥抱云原生，在今年也发布了 TiDB cloud : https://en.pingcap.com/tidb-cloud/ ，在发布之后，产品和用户数量再快速发展。我们希望能围绕 TiDB cloud 在开发生态做一些事情，给 TiDB cloud 的生态增加可能。
Think Big，act small。我们希望能在用户体验上面做一些提升，使用户更方便地拥抱 TiDB cloud native。

首先，目前用户需要登陆 TiDB cloud，进行集群创建、管理操作。之后从 TiDB cloud 上复制集群信息，并根据自己使用的框架进行配置。我们希望能做到：
1. 通过 jetbrain 插件完成 Cloud TiDB 集群的创建以及管理，搭建一道连接“云”的桥梁。
2. 集成主流框架，自动化注入 TiDB 集群配置信息。简化用户使用 TiDB cloud 的成本。 

## 项目设计

本项目主要分为 backend 以及 plugin 两个模块，其中
- Backend 部分，通过 HTTPS 协议与 TiDB cloud 后端系统通信
    - 鉴权部分，通过用户名密码获取用户鉴权信息，主要注意安全性问题。
    - 集群管理功能，包括集群的创建、清理以及修改。
    - 集群信息展示部分，实时展示集群元信息。
- Plugin 部分，完成一系列 jetbrain 插件的交互过程。
    - 用户名密码的输入，test connection。
    - 集群信息管理面板，包括集群信息的展示，以及管理按钮。
    - Spring 框架的集成，例如 find spring 配置文件后，注入生成的连接串信息。（第一步先实现为不同风格的配置 String 生成，拓展可以做自动注入[用户可选择的]）
    - 与 idea bundle 插件 Database 集成，用户能够一键生成 mysql datasource 连接 TiDB 集群（Optional）
