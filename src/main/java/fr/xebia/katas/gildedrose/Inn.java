package fr.xebia.katas.gildedrose;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.SocketConnection;

public class Inn {
	
	public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
	public static final String _5_DEXTERITY_VEST = "+5 Dexterity Vest";
	public static final String AGED_BRIE = "Aged Brie";
	
	private List<Item> items = new ArrayList<Item>();

	/**
	 * Get the item, return null if not present
	 */
	public Item getItem(final String name) {
		for(Item item : items){
			if(item.getName().equals(name)){
				return item;
			}
		}
		return null;
	}
	
	public List<Item> getItems() {
		return items;
	}
	

	public Inn() {
		items.add(new Item(AGED_BRIE, 2, 0));
		items.add(new Item(_5_DEXTERITY_VEST, 10, 20));
		items.add(new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7));
		items.add(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80));
		items.add(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
		items.add(new Item(CONJURED_MANA_CAKE, 3, 6));
	}

	   public void updateQuality() {
		      for (int i = 0; i < items.size(); i++) {
		         Item item = items.get(i);
		       
		         
			if (item.getName().equals(CONJURED_MANA_CAKE)) {

				if (item.getSellIn() > 0) {
					if (item.getQuality() > 1) {
						item.setQuality(item.getQuality() - 2);
					}
				} else {
					item.setQuality(item.getQuality() - 4);
				}
				item.setSellIn(item.getSellIn() - 1);
				continue;
			}
		         
		         
		         
				if (!item.getName().equals(AGED_BRIE) && !item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
		               if (item.getQuality() > 0 && !item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
		                  item.setQuality(item.getQuality() - 1);
		               }		               
		         } else {
		            if (item.getQuality() < 50) {
		               item.setQuality(item.getQuality() + 1);

	                     if (item.getQuality() < 50) {
		                  if ( item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT) && item.getSellIn() < 11) {
		                        item.setQuality(item.getQuality() + 1);
		                  }

		                  if (item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT) && item.getSellIn() < 6) {
		                        item.setQuality(item.getQuality() + 1);
		                  }
		            }
		            }
		         }

		         if (!item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
		            item.setSellIn(item.getSellIn() - 1);
		         }

		         if (item.getSellIn() < 0) {
		            if (!item.getName().equals(AGED_BRIE)) {
		               if (!item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
		                     if (item.getQuality() > 0 && !item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
		                        item.setQuality(item.getQuality() - 1);
		                  }
		               } else {
		                  item.setQuality(item.getQuality() - item.getQuality());
		               }
		            } else {
		               if (item.getQuality() < 50) {
		                  item.setQuality(item.getQuality() + 1);
		               }
		            }
		         }
		      }

		}

	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting to serve the world...");
		
		final Inn inn = new Inn();

		new SocketConnection(new Container() {
			@Override
			public void handle(Request req, Response resp) {
				try {
					
					inn.updateQuality();
					final StringBuilder htmlPage = new StringBuilder();
					htmlPage.append("<html>");
					htmlPage.append("<head>");
					htmlPage.append("<link href=\"http://twitter.github.com/bootstrap/assets/css/bootstrap.css\" rel=\"stylesheet\"/>");
					htmlPage.append("<link href=\"http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css\" rel=\"stylesheet\"/>");
					htmlPage.append("</head>");
					htmlPage.append("<body>");
					htmlPage.append("<div class=\"container\">");
					htmlPage.append("<h1>Welcome to Gilded Rose Inn</h1>");
					htmlPage.append("<ul>");
					for (Item item : inn.getItems()) {
						htmlPage.append(String.format("<li><strong>%s</strong> : sellIn: %d, quality is: %d</li>", item.getName(), item.getSellIn(), item.getQuality()));
					}
					
					htmlPage.append("</ul>");
					
					htmlPage.append("<button type=\"submit\" class=\"btn btn-primary\" onClick=\"javascript:window.location.reload()\">Refresh!</button>");
					htmlPage.append("</div>");
					htmlPage.append("</body></html>");
						
					resp.getPrintStream().append(htmlPage.toString()).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).connect(new InetSocketAddress(8080));
	}
}
