package com.creativemd.littletiles.common.tiles.preview;

import com.creativemd.littletiles.common.structure.LittleStructure;
import com.creativemd.littletiles.common.utils.grid.LittleGridContext;

import net.minecraft.nbt.NBTTagCompound;

public class LittlePreviewsStructure extends LittlePreviews {
	
	public NBTTagCompound nbt;
	private LittleStructure structure;
	
	public LittlePreviewsStructure(NBTTagCompound nbt, LittlePreviews previews) {
		super(previews);
		this.nbt = nbt;
	}
	
	public LittlePreviewsStructure(NBTTagCompound nbt, LittleGridContext context) {
		super(context);
		this.nbt = nbt;
	}
	
	@Override
	public boolean hasStructure() {
		return true;
	}
	
	@Override
	public void deleteCachedStructure() {
		super.deleteCachedStructure();
		structure = null;
	}
	
	@Override
	public void addChild(LittlePreviews child) {
		super.addChild(child);
		if (structure != null)
			structure.addTempChild(child.getStructure());
	}
	
	@Override
	public LittleStructure getStructure() {
		if (structure == null) {
			structure = LittleStructure.createAndLoadStructure(nbt, null);
			structure.createTempChildList();
			for (LittlePreviews child : getChildren()) {
				structure.addTempChild(child.getStructure());
			}
		}
		return structure;
	}
	
	@Override
	public NBTTagCompound getStructureData() {
		return nbt;
	}
	
	@Override
	public LittlePreviewsStructure copy() {
		LittlePreviewsStructure previews = new LittlePreviewsStructure(nbt, context);
		previews.previews.addAll(this.previews);
		previews.children.addAll(children);
		return previews;
	}
	
}
