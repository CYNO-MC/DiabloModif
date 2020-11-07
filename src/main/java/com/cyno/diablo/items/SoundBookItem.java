package com.cyno.diablo.items;

import com.cyno.diablo.client.screen.SoundBookScreen;
import com.cyno.diablo.init.SoundInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SoundBookItem extends ItemBase {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        setContents(stack);

        Minecraft.getInstance().displayGuiScreen(new SoundBookScreen(new ReadBookScreen.WrittenBookInfo(stack), false, this::pageSounds));

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void pageSounds(int page){
        List<SoundEvent> sounds = new ArrayList<>();
        for (int i=0;i<6;i++) sounds.add(SoundEvents.ITEM_BOOK_PAGE_TURN);
        sounds.add(SoundInit.STEP.get());
        sounds.add(SoundInit.WDAMAGE.get());
        sounds.add(SoundInit.WDAMAGE.get());
        for (int i=0;i<3;i++) sounds.add(SoundInit.STEP.get());
        sounds.add(SoundEvents.BLOCK_PORTAL_TRAVEL);

        if (sounds.size() > page){
            Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(sounds.get(page), 1.0F, 2.0F));
        }
    }


    private void setContents(ItemStack stack){
        CompoundNBT tag = stack.getTag();
        if (tag == null) tag = new CompoundNBT();

        tag.putString("author", getAuthor());
        tag.putString("title", getTitle());
        tag.putBoolean("resolved", true);

        ListNBT pages = getPages();
        tag.put("pages", pages);

        stack.setTag(tag);
    }

    private String getAuthor() {
        return "Cyno";
    }

    private String getTitle() {
        return "Test Book";
    }

    private ListNBT getPages(){
        ListNBT pages = new ListNBT();

        addPage("The Village of Quietude was a relatively small village. Centered right in between two cascading ravines, it became the working grounds for all aspiring Blacksmiths. This was the birthplace of Kennard Harte, a youthful lad who grew amongst the leading-", pages);
        addPage("blacksmiths in his village. Throughout his life, he was told stories and accounts of what life was like in the Nether. Unlike many of the children around him, he was interested in the idea of this new place. As time ticked forward, He began working as an-", pages);
        addPage("apprentice for the prestigious blacksmith known as Wilton Claye. He forged the finest blades and built a name while doing so. Kennard respected him. Heroes would venture out of their way just to encounter Wilton and have the chance of wielding his work.", pages);
        addPage("This took an unfortunate toll on Kennard's arrogance, as he saw himself as an extension of Wilton's success. Wilton would regularly command Kennard to fetch ore and material for his work. When out searching for new deposits, Kennard found something...", pages);
        addPage("Positioned to be facing towards the North and South, he found what seemed to be a 'gateway' with unknown origin. \"How have I not seen this before? Surely I would have noticed this. what new could this bring?\" It immediately dawned on him what this was.", pages);
        addPage("He had heard legends, but now he was faced with reality. The portal, had a feeling of life within it. He felt the urge within himself to investigate further. With his pickaxe in hand and his heart on his sleeve, Kennard Harte impetuously moved forward.....", pages);
        addPage("Digging his feet into the ground beneath him, he attempted to fight back the urge. The patterns of the portal were extremely foreign to him, yet he could hear the words it spoke:", pages);
        addPage("You seek power, Yet you hesitate. Do not cease your effort. You wonder, What is it worth? The Heart of Hell itself? Curiosity fills your mind, body, and soul. But this requires unbreakable strength. What will it take to traverse The Stygian Depths?", pages);
        addPage("It will take everything in your power. I know you will make the right choice. Opportunity is beyond what is present in the Core. It is unfathomable. Why take this chance and discard it?", pages);
        addPage("It spoke of promise and hope. It yielded potential. This could impress Wilton.", pages);
        addPage("With each step he took, his desires only became stronger...", pages);
        addPage("The urge to just walk into the portal became apparent to Kennard...", pages);
        addPage("Without a second thought, Kennard was swallowed whole by his own accord.", pages);

        return pages;
    }

    private void addPage(String content, ListNBT pages){
        INBT page = (INBT) StringNBT.valueOf("{\"text\": \"" + content + "\"}");
        // INBT page = (INBT) StringNBT.valueOf(ITextComponent.Serializer.toJson(itextcomponent));
        pages.add(page);
    }
}
