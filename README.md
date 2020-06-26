# gitbucket-3dfile-plugin

[![Build Status](https://travis-ci.org/onukura/gitbucket-3dfile-plugin.svg?branch=master)](https://travis-ci.org/onukura/gitbucket-3dfile-plugin)

A GitBucket plugin for rendering 3d file.  
Based on [three.js](https://github.com/mrdoob/three.js) and [Three.js-STL-Viewer](https://github.com/kaitlynhova/Three.js-STL-Viewer).

## Screenshot

![screenshot](https://raw.githubusercontent.com/onukura/gitbucket-3dfile-plugin/assets/screenshot.gif)

## Install

1. Download *.jar from Releases.
2. Deploy it to `GITBUCKET_HOME/plugins`.
3. Restart GitBucket.

## Build from source

```sbt
sbt clean package
```

The built package is located at
`target/scala-2.13/gitbucket-3dfile-plugin_2.13-{plugin-version}.jar`.

```sbt
sbt assembly
```

This makes the assembly package
`target/scala-2.13/gitbucket-3dfile-plugin-{plugin-version}.jar`
for deployment.

## Supported file

Currently, only `.stl` file is supported.

## Version

Plugin version|GitBucket version
:---|:---
1.0.x |4.32.x -
