# ParticleTextView

# 一、总述

ParticleTextView 是一个 Android 平台的自定义 view 组件，可以用彩色粒子组成指定的文字，并配合多种动画效果和配置属性，呈现出丰富的视觉效果。

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/Total.gif?raw=true" width="300px" height="500px">

# 二、使用

## 1. 引入依赖

```
compile 'yasic.library.ParticleTextView:particletextview:0.0.5'
```

## 2. 加入到布局文件中

```Java
    <com.yasic.library.particletextview.View.ParticleTextView
        android:id="@+id/particleTextView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

## 3. 实例化配置信息类 ParticleTextViewConfig

```Java
ParticleTextView particleTextView = (ParticleTextView) findViewById(R.id.particleTextView);
RandomMovingStrategy randomMovingStrategy = new RandomMovingStrategy();
ParticleTextViewConfig config = new ParticleTextViewConfig.Builder()
                .setRowStep(8)
                .setColumnStep(8)
                .setTargetText("Random")
                .setReleasing(0.2)
                .setParticleRadius(4)
                .setMiniDistance(0.1)
                .setTextSize(150)
                .setMovingStrategy(randomMovingStrategy)
                .instance();
particleTextView.setConfig(config);
```

## 4. 启动动画

```Java
particleTextView.startAnimation();
```

## 5. 暂停动画

```Java
particleTextView1.stopAnimation();
```

# 三、API说明

## 粒子移动轨迹策略 MovingStrategy

移动轨迹策略继承自抽象类 MovingStrategy，可以自己继承并实现其中的 setMovingPath 方法，以下是自带的几种移动轨迹策略。

* RandomMovingStrategy

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/Random.gif?raw=true" width="300px" height="500px">

* CornerStrategy

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/Corner.gif?raw=true" width="300px" height="500px">

* HorizontalStrategy

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/Horizontal.gif?raw=true" width="300px" height="500px">

* BidiHorizontalStrategy

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/BidiHorizontal.gif?raw=true" width="300px" height="500px">

* VerticalStrategy

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/Vertical.gif?raw=true" width="300px" height="500px">

* BidiVerticalStrategy

<img src="https://github.com/Yasic/ParticleTextView/blob/master/Screenshot/BidiVertical.gif?raw=true" width="300px" height="500px">

## 配置信息类 ParticleTextViewConfig

配置信息类采用工厂模式创建，以下属性均为可选属性。

* 设置显示的文字

```Java
setTargetText(String targetText)
```

* 设置文字大小

```Java
setTextSize(int textSize)
```

* 设置粒子半径

```Java
setParticleRadius(float radius)
```

* 设置横向和纵向像素采样间隔

采样间隔越小生成的粒子数目越多，但绘制帧数也随之降低，建议结合文字大小与粒子半径进行调节。

```Java
setColumnStep(int columnStep)
setRowStep(int rowStep)
```

* 设置粒子运动速度

```Java
setReleasing(double releasing)
```

指定时刻，粒子的运动速度由下列公式决定，其中 Vx 和 Vy 分别是 X 与 Y 方向上的运动速度，target 与 source 分别是粒子的目的坐标与当前坐标

```
Vx = (targetX - sourceX) * releasing
Vy = (targetY - sourceY) * releasing
```

* 设置最小判决距离

当粒子与目的坐标距离小于最小判决距离时将直接移动到目的坐标，从而减少不明显的动画过程。

```Java
setMiniDistance(double miniDistance)
```

* 设置粒子颜色域

默认使用完全随机的颜色域

```Java
setParticleColorArray(String[] particleColorArray)
```

* 设置粒子移动轨迹策略

默认使用随机分布式策略

```Java
setMovingStrategy(MovingStrategy movingStrategy)
```

* 设置不同路径间动画的间隔时间

delay < 0 时动画不循环

```Java
setDelay(Long delay)
```

## ParticleTextView类

* 指定配置信息类

```
setConfig(ParticleTextViewConfig config)
```

* 开启动画

```
void startAnimation()
```

* 停止动画

```
void stopAnimation()
```

* 获取动画是否暂停

暂停是指动画完成了一段路径后的暂留状态

```
boolean isAnimationPause()
```

* 获取动画是否停止

停止是指动画完成了一次完整路径后的停止状态

```
boolean isAnimationStop()
```

## ParticleTextSurfaceView 类

继承自 SurfaceView 类，利用子线程进行 Canvas 绘制，在多个组件渲染情况下效率更高。所有 API 与 ParticleTextView 类一致。

# License

```
Copyright 2017 Yasic

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```