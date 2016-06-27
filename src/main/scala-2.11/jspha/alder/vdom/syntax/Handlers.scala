package jspha.alder.vdom.syntax

import jspha.alder.raw.event._

class Handlers[El, Mod](syntax: Internal[El, Mod]) {

  import syntax.makeCallbackMod

  def onCopy(cb: (ClipboardEvent) => Unit): Mod = makeCallbackMod("onCopy")(cb)
  def onKeyUp(cb: (KeyboardEvent) => Unit): Mod = makeCallbackMod("onKeyUp")(cb)
  def onCut(cb: (ClipboardEvent) => Unit): Mod = makeCallbackMod("onCut")(cb)
  def onCanPlayThrough(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onCanPlayThrough")(cb)
  def onDoubleClick(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDoubleClick")(cb)
  def onDragStart(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDragStart")(cb)
  def onEmptied(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onEmptied")(cb)
  def onSubmit(cb: (FormEvent) => Unit): Mod = makeCallbackMod("onSubmit")(cb)
  def onError(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onError")(cb)
  def onErrorImg(cb: (ImageEvent) => Unit): Mod = makeCallbackMod("onError")(cb)
  def onLoadedMetadata(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onLoadedMetadata")(cb)
  def onMouseMove(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseMove")(cb)
  def onStalled(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onStalled")(cb)
  def onPlay(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onPlay")(cb)
  def onLoad(cb: (ImageEvent) => Unit): Mod = makeCallbackMod("onLoad")(cb)
  def onDrop(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDrop")(cb)
  def nDoubleClick(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("nDoubleClick")(cb)
  def onDurationChange(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onDurationChange")(cb)
  def onWaiting(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onWaiting")(cb)
  def onBlur(cb: (FocusEvent) => Unit): Mod = makeCallbackMod("onBlur")(cb)
  def onSuspend(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onSuspend")(cb)
  def onAbort(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onAbort")(cb)
  def onAnimationEnd(cb: (AnimationEvent) => Unit): Mod = makeCallbackMod("onAnimationEnd")(cb)
  def onCompositionStart(cb: (CompositionEvent) => Unit): Mod = makeCallbackMod("onCompositionStart")(cb)
  def onDrag(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDrag")(cb)
  def onMouseDown(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseDown")(cb)
  def onFocus(cb: (FocusEvent) => Unit): Mod = makeCallbackMod("onFocus")(cb)
  def onTouchMove(cb: (TouchEvent) => Unit): Mod = makeCallbackMod("onTouchMove")(cb)
  def onLoadStart(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onLoadStart")(cb)
  def onDragLeave(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDragLeave")(cb)
  def onDragEnter(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDragEnter")(cb)
  def onClick(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onClick")(cb)
  def onPause(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onPause")(cb)
  def onContextMenu(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onContextMenu")(cb)
  def onKeyDown(cb: (KeyboardEvent) => Unit): Mod = makeCallbackMod("onKeyDown")(cb)
  def onLoadedData(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onLoadedData")(cb)
  def onSelect(cb: (SelectionEvent) => Unit): Mod = makeCallbackMod("onSelect")(cb)
  def onChange(cb: (FormEvent) => Unit): Mod = makeCallbackMod("onChange")(cb)
  def onCompositionEnd(cb: (CompositionEvent) => Unit): Mod = makeCallbackMod("onCompositionEnd")(cb)
  def onTouchCancel(cb: (TouchEvent) => Unit): Mod = makeCallbackMod("onTouchCancel")(cb)
  def onVolumeChange(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onVolumeChange")(cb)
  def onMouseOver(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseOver")(cb)
  def onPaste(cb: (ClipboardEvent) => Unit): Mod = makeCallbackMod("onPaste")(cb)
  def onCanPlay(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onCanPlay")(cb)
  def onCompositionUpdate(cb: (CompositionEvent) => Unit): Mod = makeCallbackMod("onCompositionUpdate")(cb)
  def onAnimationStart(cb: (AnimationEvent) => Unit): Mod = makeCallbackMod("onAnimationStart")(cb)
  def onDragOver(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDragOver")(cb)
  def onPlaying(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onPlaying")(cb)
  def onDragEnd(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDragEnd")(cb)
  def onSeeking(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onSeeking")(cb)
  def onEnded(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onEnded")(cb)
  def onSeeked(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onSeeked")(cb)
  def onTransitionEnd(cb: (TransitionEvent) => Unit): Mod = makeCallbackMod("onTransitionEnd")(cb)
  def onAnimationIteration(cb: (AnimationEvent) => Unit): Mod = makeCallbackMod("onAnimationIteration")(cb)
  def onRateChange(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onRateChange")(cb)
  def onEncrypted(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onEncrypted")(cb)
  def onKeyPress(cb: (KeyboardEvent) => Unit): Mod = makeCallbackMod("onKeyPress")(cb)
  def onWheel(cb: (WheelEvent) => Unit): Mod = makeCallbackMod("onWheel")(cb)
  def onInput(cb: (FormEvent) => Unit): Mod = makeCallbackMod("onInput")(cb)
  def onTouchEnd(cb: (TouchEvent) => Unit): Mod = makeCallbackMod("onTouchEnd")(cb)
  def onTimeUpdate(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onTimeUpdate")(cb)
  def onMouseLeave(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseLeave")(cb)
  def onTouchStart(cb: (TouchEvent) => Unit): Mod = makeCallbackMod("onTouchStart")(cb)
  def onProgress(cb: (MediaEvent) => Unit): Mod = makeCallbackMod("onProgress")(cb)
  def onMouseOut(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseOut")(cb)
  def onScroll(cb: (UIEvent) => Unit): Mod = makeCallbackMod("onScroll")(cb)
  def onMouseEnter(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseEnter")(cb)
  def onDragExit(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onDragExit")(cb)
  def onMouseUp(cb: (MouseEvent) => Unit): Mod = makeCallbackMod("onMouseUp")(cb)

}
