package com.locydragon.lagg.neural.data;

import com.google.common.collect.Maps;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.bukkit.material.Banner;
import org.bukkit.material.Bed;
import org.bukkit.material.Button;
import org.bukkit.material.Cake;
import org.bukkit.material.Cauldron;
import org.bukkit.material.Chest;
import org.bukkit.material.Coal;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Command;
import org.bukkit.material.Crops;
import org.bukkit.material.DetectorRail;
import org.bukkit.material.Diode;
import org.bukkit.material.Dispenser;
import org.bukkit.material.Door;
import org.bukkit.material.Dye;
import org.bukkit.material.EnderChest;
import org.bukkit.material.FlowerPot;
import org.bukkit.material.Furnace;
import org.bukkit.material.Gate;
import org.bukkit.material.Ladder;
import org.bukkit.material.Leaves;
import org.bukkit.material.Lever;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.MonsterEggs;
import org.bukkit.material.Mushroom;
import org.bukkit.material.NetherWarts;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.PistonExtensionMaterial;
import org.bukkit.material.PoweredRail;
import org.bukkit.material.PressurePlate;
import org.bukkit.material.Pumpkin;
import org.bukkit.material.Rails;
import org.bukkit.material.RedstoneTorch;
import org.bukkit.material.RedstoneWire;
import org.bukkit.material.Sandstone;
import org.bukkit.material.Sign;
import org.bukkit.material.Skull;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.SpawnEgg;
import org.bukkit.material.Stairs;
import org.bukkit.material.Step;
import org.bukkit.material.Torch;
import org.bukkit.material.TrapDoor;
import org.bukkit.material.Tree;
import org.bukkit.material.Tripwire;
import org.bukkit.material.TripwireHook;
import org.bukkit.material.Vine;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Wool;

/**
 * Code from spigot core(jd-gui)
 */
public enum Material
{
  AIR(0, 0), 
  STONE(1), 
  GRASS(2), 
  DIRT(3), 
  COBBLESTONE(4), 
  WOOD(5),
  SAPLING(6),
  BEDROCK(7), 
  WATER(8, MaterialData.class), 
  STATIONARY_WATER(9, MaterialData.class), 
  LAVA(10, MaterialData.class), 
  STATIONARY_LAVA(11, MaterialData.class), 
  SAND(12), 
  GRAVEL(13), 
  GOLD_ORE(14), 
  IRON_ORE(15), 
  COAL_ORE(16), 
  LOG(17, Tree.class), 
  LEAVES(18, Leaves.class), 
  SPONGE(19), 
  GLASS(20), 
  LAPIS_ORE(21), 
  LAPIS_BLOCK(22), 
  DISPENSER(23, Dispenser.class), 
  SANDSTONE(24, Sandstone.class), 
  NOTE_BLOCK(25), 
  BED_BLOCK(26, Bed.class), 
  POWERED_RAIL(27, PoweredRail.class), 
  DETECTOR_RAIL(28, DetectorRail.class), 
  PISTON_STICKY_BASE(29, PistonBaseMaterial.class), 
  WEB(30), 
  LONG_GRASS(31, LongGrass.class), 
  DEAD_BUSH(32), 
  PISTON_BASE(33, PistonBaseMaterial.class), 
  PISTON_EXTENSION(34, PistonExtensionMaterial.class), 
  WOOL(35, Wool.class), 
  PISTON_MOVING_PIECE(36), 
  YELLOW_FLOWER(37), 
  RED_ROSE(38), 
  BROWN_MUSHROOM(39), 
  RED_MUSHROOM(40), 
  GOLD_BLOCK(41), 
  IRON_BLOCK(42), 
  DOUBLE_STEP(43, Step.class), 
  STEP(44, Step.class), 
  BRICK(45), 
  TNT(46), 
  BOOKSHELF(47), 
  MOSSY_COBBLESTONE(48), 
  OBSIDIAN(49), 
  TORCH(50, Torch.class), 
  FIRE(51), 
  MOB_SPAWNER(52), 
  WOOD_STAIRS(53, Stairs.class), 
  CHEST(54, Chest.class), 
  REDSTONE_WIRE(55, RedstoneWire.class), 
  DIAMOND_ORE(56), 
  DIAMOND_BLOCK(57), 
  WORKBENCH(58), 
  CROPS(59, Crops.class), 
  SOIL(60, MaterialData.class), 
  FURNACE(61, Furnace.class), 
  BURNING_FURNACE(62, Furnace.class), 
  SIGN_POST(63, 64, Sign.class), 
  WOODEN_DOOR(64, Door.class), 
  LADDER(65, Ladder.class), 
  RAILS(66, Rails.class), 
  COBBLESTONE_STAIRS(67, Stairs.class), 
  WALL_SIGN(68, 64, Sign.class), 
  LEVER(69, Lever.class), 
  STONE_PLATE(70, PressurePlate.class), 
  IRON_DOOR_BLOCK(71, Door.class), 
  WOOD_PLATE(72, PressurePlate.class), 
  REDSTONE_ORE(73), 
  GLOWING_REDSTONE_ORE(74), 
  REDSTONE_TORCH_OFF(75, RedstoneTorch.class), 
  REDSTONE_TORCH_ON(76, RedstoneTorch.class), 
  STONE_BUTTON(77, Button.class), 
  SNOW(78), 
  ICE(79), 
  SNOW_BLOCK(80), 
  CACTUS(81, MaterialData.class), 
  CLAY(82), 
  SUGAR_CANE_BLOCK(83, MaterialData.class), 
  JUKEBOX(84), 
  FENCE(85), 
  PUMPKIN(86, Pumpkin.class), 
  NETHERRACK(87), 
  SOUL_SAND(88), 
  GLOWSTONE(89), 
  PORTAL(90), 
  JACK_O_LANTERN(91, Pumpkin.class), 
  CAKE_BLOCK(92, 64, Cake.class), 
  DIODE_BLOCK_OFF(93, Diode.class), 
  DIODE_BLOCK_ON(94, Diode.class), 
  STAINED_GLASS(95), 
  TRAP_DOOR(96, TrapDoor.class), 
  MONSTER_EGGS(97, MonsterEggs.class), 
  SMOOTH_BRICK(98, SmoothBrick.class), 
  HUGE_MUSHROOM_1(99, Mushroom.class), 
  HUGE_MUSHROOM_2(100, Mushroom.class), 
  IRON_FENCE(101), 
  THIN_GLASS(102), 
  MELON_BLOCK(103), 
  PUMPKIN_STEM(104, MaterialData.class), 
  MELON_STEM(105, MaterialData.class), 
  VINE(106, Vine.class), 
  FENCE_GATE(107, Gate.class), 
  BRICK_STAIRS(108, Stairs.class), 
  SMOOTH_STAIRS(109, Stairs.class), 
  MYCEL(110), 
  WATER_LILY(111), 
  NETHER_BRICK(112), 
  NETHER_FENCE(113), 
  NETHER_BRICK_STAIRS(114, Stairs.class), 
  NETHER_WARTS(115, NetherWarts.class), 
  ENCHANTMENT_TABLE(116), 
  BREWING_STAND(117, MaterialData.class), 
  CAULDRON(118, Cauldron.class), 
  ENDER_PORTAL(119), 
  ENDER_PORTAL_FRAME(120), 
  ENDER_STONE(121), 
  DRAGON_EGG(122), 
  REDSTONE_LAMP_OFF(123), 
  REDSTONE_LAMP_ON(124), 
  WOOD_DOUBLE_STEP(125),
  WOOD_STEP(126, WoodenStep.class), 
  COCOA(127, CocoaPlant.class), 
  SANDSTONE_STAIRS(128, Stairs.class), 
  EMERALD_ORE(129), 
  ENDER_CHEST(130, EnderChest.class), 
  TRIPWIRE_HOOK(131, TripwireHook.class), 
  TRIPWIRE(132, Tripwire.class), 
  EMERALD_BLOCK(133), 
  SPRUCE_WOOD_STAIRS(134, Stairs.class), 
  BIRCH_WOOD_STAIRS(135, Stairs.class), 
  JUNGLE_WOOD_STAIRS(136, Stairs.class), 
  COMMAND(137, Command.class), 
  BEACON(138), 
  COBBLE_WALL(139), 
  FLOWER_POT(140, FlowerPot.class), 
  CARROT(141, Crops.class), 
  POTATO(142, Crops.class), 
  WOOD_BUTTON(143, Button.class), 
  SKULL(144, Skull.class), 
  ANVIL(145), 
  TRAPPED_CHEST(146, Chest.class), 
  GOLD_PLATE(147), 
  IRON_PLATE(148), 
  REDSTONE_COMPARATOR_OFF(149),
  REDSTONE_COMPARATOR_ON(150),
  DAYLIGHT_DETECTOR(151), 
  REDSTONE_BLOCK(152), 
  QUARTZ_ORE(153), 
  HOPPER(154),
  QUARTZ_BLOCK(155), 
  QUARTZ_STAIRS(156, Stairs.class), 
  ACTIVATOR_RAIL(157, PoweredRail.class), 
  DROPPER(158, Dispenser.class), 
  STAINED_CLAY(159), 
  STAINED_GLASS_PANE(160), 
  LEAVES_2(161, Leaves.class), 
  LOG_2(162, Tree.class), 
  ACACIA_STAIRS(163, Stairs.class), 
  DARK_OAK_STAIRS(164, Stairs.class), 
  SLIME_BLOCK(165), 
  BARRIER(166), 
  IRON_TRAPDOOR(167, TrapDoor.class), 
  PRISMARINE(168), 
  SEA_LANTERN(169), 
  HAY_BLOCK(170), 
  CARPET(171), 
  HARD_CLAY(172), 
  COAL_BLOCK(173), 
  PACKED_ICE(174), 
  DOUBLE_PLANT(175), 
  STANDING_BANNER(176, Banner.class), 
  WALL_BANNER(177, Banner.class), 
  DAYLIGHT_DETECTOR_INVERTED(178), 
  RED_SANDSTONE(179), 
  RED_SANDSTONE_STAIRS(180, Stairs.class), 
  DOUBLE_STONE_SLAB2(181), 
  STONE_SLAB2(182), 
  SPRUCE_FENCE_GATE(183, Gate.class), 
  BIRCH_FENCE_GATE(184, Gate.class), 
  JUNGLE_FENCE_GATE(185, Gate.class), 
  DARK_OAK_FENCE_GATE(186, Gate.class), 
  ACACIA_FENCE_GATE(187, Gate.class), 
  SPRUCE_FENCE(188), 
  BIRCH_FENCE(189), 
  JUNGLE_FENCE(190), 
  DARK_OAK_FENCE(191), 
  ACACIA_FENCE(192), 
  SPRUCE_DOOR(193, Door.class), 
  BIRCH_DOOR(194, Door.class), 
  JUNGLE_DOOR(195, Door.class), 
  ACACIA_DOOR(196, Door.class), 
  DARK_OAK_DOOR(197, Door.class), 
  END_ROD(198), 
  CHORUS_PLANT(199), 
  CHORUS_FLOWER(200), 
  PURPUR_BLOCK(201), 
  PURPUR_PILLAR(202), 
  PURPUR_STAIRS(203, Stairs.class), 
  PURPUR_DOUBLE_SLAB(204), 
  PURPUR_SLAB(205), 
  END_BRICKS(206), 
  BEETROOT_BLOCK(207, Crops.class), 
  GRASS_PATH(208), 
  END_GATEWAY(209), 
  COMMAND_REPEATING(210, Command.class), 
  COMMAND_CHAIN(211, Command.class), 
  FROSTED_ICE(212), 
  MAGMA(213), 
  NETHER_WART_BLOCK(214), 
  RED_NETHER_BRICK(215), 
  BONE_BLOCK(216), 
  STRUCTURE_VOID(217), 
  OBSERVER(218),
  WHITE_SHULKER_BOX(219, 1), 
  ORANGE_SHULKER_BOX(220, 1), 
  MAGENTA_SHULKER_BOX(221, 1), 
  LIGHT_BLUE_SHULKER_BOX(222, 1), 
  YELLOW_SHULKER_BOX(223, 1), 
  LIME_SHULKER_BOX(224, 1), 
  PINK_SHULKER_BOX(225, 1), 
  GRAY_SHULKER_BOX(226, 1), 
  SILVER_SHULKER_BOX(227, 1), 
  CYAN_SHULKER_BOX(228, 1), 
  PURPLE_SHULKER_BOX(229, 1), 
  BLUE_SHULKER_BOX(230, 1), 
  BROWN_SHULKER_BOX(231, 1), 
  GREEN_SHULKER_BOX(232, 1), 
  RED_SHULKER_BOX(233, 1), 
  BLACK_SHULKER_BOX(234, 1), 
  STRUCTURE_BLOCK(255), 
  IRON_SPADE(
    256, 1, 250), 
  IRON_PICKAXE(257, 1, 250), 
  IRON_AXE(258, 1, 250), 
  FLINT_AND_STEEL(259, 1, 64), 
  APPLE(260), 
  BOW(261, 1, 384), 
  ARROW(262), 
  COAL(263, Coal.class), 
  DIAMOND(264), 
  IRON_INGOT(265), 
  GOLD_INGOT(266), 
  IRON_SWORD(267, 1, 250), 
  WOOD_SWORD(268, 1, 59), 
  WOOD_SPADE(269, 1, 59), 
  WOOD_PICKAXE(270, 1, 59), 
  WOOD_AXE(271, 1, 59), 
  STONE_SWORD(272, 1, 131), 
  STONE_SPADE(273, 1, 131), 
  STONE_PICKAXE(274, 1, 131), 
  STONE_AXE(275, 1, 131), 
  DIAMOND_SWORD(276, 1, 1561), 
  DIAMOND_SPADE(277, 1, 1561), 
  DIAMOND_PICKAXE(278, 1, 1561), 
  DIAMOND_AXE(279, 1, 1561), 
  STICK(280), 
  BOWL(281), 
  MUSHROOM_SOUP(282, 1), 
  GOLD_SWORD(283, 1, 32), 
  GOLD_SPADE(284, 1, 32), 
  GOLD_PICKAXE(285, 1, 32), 
  GOLD_AXE(286, 1, 32), 
  STRING(287), 
  FEATHER(288), 
  SULPHUR(289), 
  WOOD_HOE(290, 1, 59), 
  STONE_HOE(291, 1, 131), 
  IRON_HOE(292, 1, 250), 
  DIAMOND_HOE(293, 1, 1561), 
  GOLD_HOE(294, 1, 32), 
  SEEDS(295), 
  WHEAT(296), 
  BREAD(297), 
  LEATHER_HELMET(298, 1, 55), 
  LEATHER_CHESTPLATE(299, 1, 80), 
  LEATHER_LEGGINGS(300, 1, 75), 
  LEATHER_BOOTS(301, 1, 65), 
  CHAINMAIL_HELMET(302, 1, 165), 
  CHAINMAIL_CHESTPLATE(303, 1, 240), 
  CHAINMAIL_LEGGINGS(304, 1, 225), 
  CHAINMAIL_BOOTS(305, 1, 195), 
  IRON_HELMET(306, 1, 165), 
  IRON_CHESTPLATE(307, 1, 240), 
  IRON_LEGGINGS(308, 1, 225), 
  IRON_BOOTS(309, 1, 195), 
  DIAMOND_HELMET(310, 1, 363), 
  DIAMOND_CHESTPLATE(311, 1, 528), 
  DIAMOND_LEGGINGS(312, 1, 495), 
  DIAMOND_BOOTS(313, 1, 429), 
  GOLD_HELMET(314, 1, 77), 
  GOLD_CHESTPLATE(315, 1, 112), 
  GOLD_LEGGINGS(316, 1, 105), 
  GOLD_BOOTS(317, 1, 91), 
  FLINT(318), 
  PORK(319), 
  GRILLED_PORK(320), 
  PAINTING(321), 
  GOLDEN_APPLE(322), 
  SIGN(323, 16), 
  WOOD_DOOR(324, 64), 
  BUCKET(325, 16), 
  WATER_BUCKET(326, 1), 
  LAVA_BUCKET(327, 1), 
  MINECART(328, 1), 
  SADDLE(329, 1), 
  IRON_DOOR(330, 64), 
  REDSTONE(331), 
  SNOW_BALL(332, 16), 
  BOAT(333, 1), 
  LEATHER(334), 
  MILK_BUCKET(335, 1), 
  CLAY_BRICK(336), 
  CLAY_BALL(337), 
  SUGAR_CANE(338), 
  PAPER(339), 
  BOOK(340), 
  SLIME_BALL(341), 
  STORAGE_MINECART(342, 1), 
  POWERED_MINECART(343, 1), 
  EGG(344, 16), 
  COMPASS(345), 
  FISHING_ROD(346, 1, 64), 
  WATCH(347), 
  GLOWSTONE_DUST(348), 
  RAW_FISH(349), 
  COOKED_FISH(350), 
  INK_SACK(351, Dye.class), 
  BONE(352), 
  SUGAR(353), 
  CAKE(354, 1), 
  BED(355, 1), 
  DIODE(356), 
  COOKIE(357), 
  MAP(
    358, MaterialData.class), 
  SHEARS(359, 1, 238), 
  MELON(360), 
  PUMPKIN_SEEDS(361), 
  MELON_SEEDS(362), 
  RAW_BEEF(363), 
  COOKED_BEEF(364), 
  RAW_CHICKEN(365), 
  COOKED_CHICKEN(366), 
  ROTTEN_FLESH(367), 
  ENDER_PEARL(368, 16), 
  BLAZE_ROD(369), 
  GHAST_TEAR(370), 
  GOLD_NUGGET(371), 
  NETHER_STALK(372), 
  POTION(373, 1, MaterialData.class), 
  GLASS_BOTTLE(374), 
  SPIDER_EYE(375), 
  FERMENTED_SPIDER_EYE(376), 
  BLAZE_POWDER(377), 
  MAGMA_CREAM(378), 
  BREWING_STAND_ITEM(379), 
  CAULDRON_ITEM(380), 
  EYE_OF_ENDER(381), 
  SPECKLED_MELON(382), 
  MONSTER_EGG(383, 64, SpawnEgg.class), 
  EXP_BOTTLE(384, 64), 
  FIREBALL(385, 64), 
  BOOK_AND_QUILL(386, 1), 
  WRITTEN_BOOK(387, 16), 
  EMERALD(388, 64), 
  ITEM_FRAME(389), 
  FLOWER_POT_ITEM(390), 
  CARROT_ITEM(391), 
  POTATO_ITEM(392), 
  BAKED_POTATO(393), 
  POISONOUS_POTATO(394), 
  EMPTY_MAP(395), 
  GOLDEN_CARROT(396), 
  SKULL_ITEM(397), 
  CARROT_STICK(398, 1, 25), 
  NETHER_STAR(399), 
  PUMPKIN_PIE(400), 
  FIREWORK(401), 
  FIREWORK_CHARGE(402), 
  ENCHANTED_BOOK(403, 1), 
  REDSTONE_COMPARATOR(404), 
  NETHER_BRICK_ITEM(405), 
  QUARTZ(406), 
  EXPLOSIVE_MINECART(407, 1), 
  HOPPER_MINECART(408, 1), 
  PRISMARINE_SHARD(409), 
  PRISMARINE_CRYSTALS(410), 
  RABBIT(411), 
  COOKED_RABBIT(412), 
  RABBIT_STEW(413, 1), 
  RABBIT_FOOT(414), 
  RABBIT_HIDE(415), 
  ARMOR_STAND(416, 16), 
  IRON_BARDING(417, 1), 
  GOLD_BARDING(418, 1), 
  DIAMOND_BARDING(419, 1), 
  LEASH(420), 
  NAME_TAG(421), 
  COMMAND_MINECART(422, 1), 
  MUTTON(423), 
  COOKED_MUTTON(424), 
  BANNER(425, 16), 
  END_CRYSTAL(426), 
  SPRUCE_DOOR_ITEM(427), 
  BIRCH_DOOR_ITEM(428), 
  JUNGLE_DOOR_ITEM(429), 
  ACACIA_DOOR_ITEM(430), 
  DARK_OAK_DOOR_ITEM(431), 
  CHORUS_FRUIT(432), 
  CHORUS_FRUIT_POPPED(433), 
  BEETROOT(434), 
  BEETROOT_SEEDS(435), 
  BEETROOT_SOUP(436, 1), 
  DRAGONS_BREATH(437), 
  SPLASH_POTION(438, 1), 
  SPECTRAL_ARROW(439), 
  TIPPED_ARROW(440), 
  LINGERING_POTION(441, 1), 
  SHIELD(442, 1, 336), 
  ELYTRA(443, 1, 431), 
  BOAT_SPRUCE(444, 1), 
  BOAT_BIRCH(445, 1), 
  BOAT_JUNGLE(446, 1), 
  BOAT_ACACIA(447, 1), 
  BOAT_DARK_OAK(448, 1), 
  TOTEM(449, 1), 
  SHULKER_SHELL(450), 
  IRON_NUGGET(452), 
  GOLD_RECORD(2256, 1), 
  GREEN_RECORD(2257, 1), 
  RECORD_3(2258, 1), 
  RECORD_4(2259, 1), 
  RECORD_5(2260, 1), 
  RECORD_6(2261, 1), 
  RECORD_7(2262, 1), 
  RECORD_8(2263, 1), 
  RECORD_9(2264, 1), 
  RECORD_10(2265, 1), 
  RECORD_11(2266, 1), 
  RECORD_12(2267, 1);

  private final int id;
  private final Constructor<? extends MaterialData> ctor;
  private static Material[] byId;
  private static final Map<String, Material> BY_NAME;
  private final int maxStack;
  private final short durability;

  static { byId = new Material[383];
    BY_NAME = Maps.newHashMap();

    for (Material material : values()) {
      if (byId.length > material.id) {
        byId[material.id] = material;
      } else {
        byId = (Material[])Arrays.copyOfRange(byId, 0, material.id + 2);
        byId[material.id] = material;
      }
      BY_NAME.put(material.name(), material);
    }
  }

  private Material(int id)
  {
    this(id, 64);
  }

  private Material(int id, int stack) {
    this(id, stack, MaterialData.class);
  }

  private Material(int id, int stack, int durability) {
    this(id, stack, durability, MaterialData.class);
  }

  private Material(int id, Class<? extends MaterialData> data) {
    this(id, 64, data);
  }

  private Material(int id, int stack, Class<? extends MaterialData> data) {
    this(id, stack, 0, data);
  }

  private Material(int id, int stack, int durability, Class<? extends MaterialData> data) {
    this.id = id;
    this.durability = ((short)durability);
    this.maxStack = stack;
    try
    {
      this.ctor = data.getConstructor(new Class[] { Integer.TYPE, Byte.TYPE });
    } catch (NoSuchMethodException ex) {
      throw new AssertionError(ex);
    } catch (SecurityException ex) {
      throw new AssertionError(ex);
    }
  }

  @Deprecated
  public int getId()
  {
    return this.id;
  }

  public int getMaxStackSize()
  {
    return this.maxStack;
  }

  public short getMaxDurability()
  {
    return this.durability;
  }

  public Class<? extends MaterialData> getData()
  {
    return this.ctor.getDeclaringClass();
  }

  @Deprecated
  public MaterialData getNewData(byte raw)
  {
    try
    {
      return (MaterialData)this.ctor.newInstance(new Object[] { Integer.valueOf(this.id), Byte.valueOf(raw) });
    } catch (InstantiationException ex) {
      Throwable t = ex.getCause();
      if ((t instanceof RuntimeException)) {
        throw ((RuntimeException)t);
      }
      if ((t instanceof Error)) {
        throw ((Error)t);
      }
      throw new AssertionError(t);
    } catch (Throwable t) {
      throw new AssertionError(t);
    }
  }

  public boolean isBlock()
  {
    return this.id < 256;
  }


  @Deprecated
  public static Material getMaterial(int id)
  {
    if ((byId.length > id) && (id >= 0)) {
      return byId[id];
    }
    return null;
  }

  public static org.bukkit.Material getRealMaterial(int id) {
    return org.bukkit.Material.getMaterial(getMaterial(id).toString());
  }
  public static Material getMaterial(String name)
  {
    return (Material)BY_NAME.get(name);
  }

  public static Material matchMaterial(String name)
  {
    Validate.notNull(name, "Name cannot be null");

    Material result = null;
    try
    {
      result = getMaterial(Integer.parseInt(name));
    } catch (NumberFormatException localNumberFormatException) {
    }
    if (result == null) {
      String filtered = name.toUpperCase(Locale.ENGLISH);

      filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
      result = (Material)BY_NAME.get(filtered);
    }

    return result;
  }

  public boolean isRecord()
  {
    return (this.id >= GOLD_RECORD.id) && (this.id <= RECORD_12.id);
  }

  public boolean isSolid()
  {
    if ((!isBlock()) || (this.id == 0)) {
      return false;
    }
    switch (this) {
    case ACACIA_DOOR_ITEM:
    case ACACIA_FENCE:
    case ACACIA_FENCE_GATE:
    case ACACIA_STAIRS:
    case ACTIVATOR_RAIL:
    case ANVIL:
    case BANNER:
    case BARRIER:
    case BEACON:
    case BED:
    case BEDROCK:
    case BED_BLOCK:
    case BEETROOT:
    case BEETROOT_BLOCK:
    case BEETROOT_SEEDS:
    case BEETROOT_SOUP:
    case BIRCH_DOOR:
    case BIRCH_DOOR_ITEM:
    case BIRCH_FENCE:
    case BIRCH_FENCE_GATE:
    case BIRCH_WOOD_STAIRS:
    case BLAZE_ROD:
    case BOAT_BIRCH:
    case BOAT_DARK_OAK:
    case BOAT_JUNGLE:
    case BOAT_SPRUCE:
    case BOOK_AND_QUILL:
    case BOW:
    case BOWL:
    case BREAD:
    case BREWING_STAND:
    case BREWING_STAND_ITEM:
    case BRICK:
    case BRICK_STAIRS:
    case BROWN_MUSHROOM:
    case BURNING_FURNACE:
    case CACTUS:
    case CAKE:
    case CARPET:
    case CARROT:
    case CARROT_ITEM:
    case CAULDRON:
    case CAULDRON_ITEM:
    case CHAINMAIL_BOOTS:
    case CHAINMAIL_CHESTPLATE:
    case CHAINMAIL_HELMET:
    case CHORUS_FLOWER:
    case CHORUS_FRUIT:
    case CHORUS_PLANT:
    case CLAY:
    case CLAY_BALL:
    case CLAY_BRICK:
    case COAL:
    case COBBLE_WALL:
    case COCOA:
    case COMMAND:
    case COMMAND_CHAIN:
    case COMMAND_REPEATING:
    case COMPASS:
    case COOKED_BEEF:
    case COOKED_CHICKEN:
    case COOKED_FISH:
    case COOKED_MUTTON:
    case COOKIE:
    case CROPS:
    case DARK_OAK_DOOR_ITEM:
    case DARK_OAK_FENCE:
    case DARK_OAK_FENCE_GATE:
    case DARK_OAK_STAIRS:
    case DAYLIGHT_DETECTOR:
    case DAYLIGHT_DETECTOR_INVERTED:
    case DEAD_BUSH:
    case DETECTOR_RAIL:
    case DIAMOND:
    case DIAMOND_BOOTS:
    case DIAMOND_CHESTPLATE:
    case DIAMOND_HELMET:
    case DIAMOND_HOE:
    case DIAMOND_ORE:
    case DIAMOND_PICKAXE:
    case DIAMOND_SPADE:
    case DIODE:
    case DIODE_BLOCK_OFF:
    case DIODE_BLOCK_ON:
    case DISPENSER:
    case DOUBLE_PLANT:
    case DOUBLE_STEP:
    case DOUBLE_STONE_SLAB2:
    case DRAGONS_BREATH:
    case DRAGON_EGG:
    case DROPPER:
    case ELYTRA:
    case EMERALD:
    case EMERALD_BLOCK:
    case ENCHANTED_BOOK:
    case ENCHANTMENT_TABLE:
    case ENDER_CHEST:
    case ENDER_PEARL:
    case ENDER_PORTAL:
    case ENDER_PORTAL_FRAME:
    case ENDER_STONE:
    case EXP_BOTTLE:
    case EYE_OF_ENDER:
    case FEATHER:
    case FENCE:
    case FIRE:
    case FIREBALL:
    case FIREWORK:
    case FIREWORK_CHARGE:
    case FISHING_ROD:
    case FLINT:
    case FLOWER_POT:
    case FLOWER_POT_ITEM:
    case FROSTED_ICE:
    case FURNACE:
    case GHAST_TEAR:
    case GLASS:
    case GLASS_BOTTLE:
    case GLOWING_REDSTONE_ORE:
    case GLOWSTONE:
    case GLOWSTONE_DUST:
    case GOLDEN_APPLE:
    case GOLDEN_CARROT:
    case GOLD_AXE:
    case GOLD_BLOCK:
    case GOLD_BOOTS:
    case GOLD_CHESTPLATE:
    case GOLD_HOE:
    case GOLD_INGOT:
    case GOLD_LEGGINGS:
    case GOLD_NUGGET:
    case GOLD_ORE:
    case GOLD_PICKAXE:
    case GOLD_PLATE:
    case GOLD_RECORD:
    case GOLD_SPADE:
    case GOLD_SWORD:
    case GRASS:
    case GRASS_PATH:
    case GRAVEL:
    case GRAY_SHULKER_BOX:
    case GREEN_RECORD:
    case GREEN_SHULKER_BOX:
    case GRILLED_PORK:
    case HARD_CLAY:
    case HAY_BLOCK:
    case HOPPER:
    case HOPPER_MINECART:
    case HUGE_MUSHROOM_1:
    case IRON_AXE:
    case IRON_BARDING:
    case IRON_BLOCK:
    case IRON_BOOTS:
    case IRON_CHESTPLATE:
    case IRON_DOOR:
    case IRON_FENCE:
    case IRON_HOE:
    case IRON_INGOT:
    case IRON_LEGGINGS:
    case IRON_NUGGET:
    case IRON_ORE:
    case IRON_PICKAXE:
    case IRON_PLATE:
    case IRON_SWORD:
    case IRON_TRAPDOOR:
    case ITEM_FRAME:
    case JACK_O_LANTERN:
    case JUKEBOX:
    case JUNGLE_DOOR:
    case JUNGLE_DOOR_ITEM:
    case JUNGLE_FENCE:
    case JUNGLE_FENCE_GATE:
    case JUNGLE_WOOD_STAIRS:
    case LADDER:
    case LAPIS_BLOCK:
    case LAPIS_ORE:
    case LAVA:
    case LAVA_BUCKET:
    case LEASH:
    case LEATHER:
    case LEATHER_BOOTS:
      return true;
    case AIR:
    case APPLE:
    case ARMOR_STAND:
    case ARROW:
    case BAKED_POTATO:
    case BLACK_SHULKER_BOX:
    case BLAZE_POWDER:
    case BLUE_SHULKER_BOX:
    case BOAT:
    case BOAT_ACACIA:
    case BONE:
    case BONE_BLOCK:
    case BOOK:
    case BOOKSHELF:
    case BROWN_SHULKER_BOX:
    case BUCKET:
    case CAKE_BLOCK:
    case CARROT_STICK:
    case CHAINMAIL_LEGGINGS:
    case CHEST:
    case CHORUS_FRUIT_POPPED:
    case COAL_BLOCK:
    case COAL_ORE:
    case COBBLESTONE:
    case COBBLESTONE_STAIRS:
    case COMMAND_MINECART:
    case COOKED_RABBIT:
    case CYAN_SHULKER_BOX:
    case DARK_OAK_DOOR:
    case DIAMOND_AXE:
    case DIAMOND_BARDING:
    case DIAMOND_BLOCK:
    case DIAMOND_LEGGINGS:
    case DIAMOND_SWORD:
    case DIRT:
    case EGG:
    case EMERALD_ORE:
    case EMPTY_MAP:
    case END_BRICKS:
    case END_CRYSTAL:
    case END_GATEWAY:
    case END_ROD:
    case EXPLOSIVE_MINECART:
    case FENCE_GATE:
    case FERMENTED_SPIDER_EYE:
    case FLINT_AND_STEEL:
    case GOLD_BARDING:
    case GOLD_HELMET:
    case HUGE_MUSHROOM_2:
    case ICE:
    case INK_SACK:
    case IRON_DOOR_BLOCK:
    case IRON_HELMET:
    case IRON_SPADE: } return false;
  }

}