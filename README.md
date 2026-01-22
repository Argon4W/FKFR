![logo](src/main/resources/logo.png)

# FKFR ([EN version](#english))

## 🖥️模组介绍
一个简单的通过RenderTypeSet限制不正确访问SimpleBakedModel面数据的Minecraft模组, 
旨在于修复Forgified Fabric Renderer API (FFR API) 造成的在NeoForge上渲染的不一致性, 如在地形中渲染ModelPart模型的视觉效果与性能异常. 

## ✨为什么需要这个MOD
NeoForge拓展了Minecraft原本的模型系统, 允许其使用不同的RenderType进行渲染, 并因此修补了MultiPart模型类, 以让每一个部件都可以使用不同的RenderType. 
而Fabric Renderer API (FR API) 并没有未原版模型 (如MultiPart模型) 实现该功能, 而Sinytra在将FR API移植到NeoForge上时并没有注意到两者的差异, 
强行让模型使用了Fabric的渲染路径, 导致本应在不同RenderType中渲染的部件被放到了所有RenderType中, 造成性能与视觉效果异常.

## ⚙️工作原理
FKFR通过SpongePowered Mixin修改SimpleBakedModel, 将本来应该在NeoForge拓展过的MultiPart模型管线的渲染条件判断同时在最底层的SimpleBakedModel中生效, 
让其在不应该被渲染时 (如RenderType不正确时) 返回空的面数据列表, 防止模型被传入错误的RenderType中.

<a id="english"></a>
# FKFR

## 🖥️MOD Description
A simple Minecraft MOD that prevents illegal accessing to quads data of SimpleModel by RenderTypeSet, 
aiming to fix rendering inconsistency introduced by Forgified Fabric Renderer API (FFR API) such as visual and performance issue when rendering ModelPart models in terrain.

## ✨Why need this MOD
NeoForge extended the original model system of Minecraft, allowing it to use different RenderTypes when rendering different parts of multipart models.
However, Fabric Renderer API (FR API) did not implement that feature for vanilla models (such as multipart models), 
while Sinytra has not noticed the difference of them and force models to render in fabric's pipeline that does not care about NeoForge's RenderType setting, 
which caused all parts that should be rendered into different RenderTypes separately to be rendered into all RenderTypes all together.

## ⚙️How it works
FKFR modifies SimpleBakedModel by SpongePowered Mixin to brings in the rendering condition check that originally should happen inside NeoForge's extended multipart model pipeline.
It will make models not rendering when they found they are in the wrong rendering state such as incorrect RenderTypes. 