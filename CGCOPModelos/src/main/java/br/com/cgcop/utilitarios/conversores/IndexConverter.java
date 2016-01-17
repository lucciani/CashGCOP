package br.com.cgcop.utilitarios.conversores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author gilmario
 */
//@FacesConverter(value="indexConverter")
//@Named
public class IndexConverter implements Converter {

    private int index = -1;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {

        SelectItem selectedItem = this.getSelectedItemByIndex(component, Integer.parseInt(value));
        if (selectedItem != null) {
            return selectedItem.getValue();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
        index++;
        return String.valueOf(index);
    }

    protected SelectItem getSelectedItemByIndex(UIComponent component, int index) {

        List<SelectItem> items = this.getSelectItems(component);
        int size = items.size();

        if (index > -1
                && size > index) {
            return items.get(index);
        }

        return null;
    }

    protected List<SelectItem> getSelectItems(UIComponent component) {
        List<SelectItem> items = new ArrayList<>();
        int childCount = component.getChildCount();
        if (childCount == 0) {
            return items;
        }
        List<UIComponent> children = component.getChildren();
        for (UIComponent child : children) {
            if (child instanceof UISelectItem) {
                this.addSelectItem((UISelectItem) child, items);
            } else if (child instanceof UISelectItems) {
                this.addSelectItems((UISelectItems) child, items);
            }
        }
        return items;
    }

    protected void addSelectItem(UISelectItem uiItem, List<SelectItem> items) {
        boolean isRendered = uiItem.isRendered();
        if (!isRendered) {
            items.add(null);
            return;
        }
        Object value = uiItem.getValue();
        SelectItem item;
        if (value instanceof SelectItem) {
            item = (SelectItem) value;
        } else {
            Object itemValue = uiItem.getItemValue();
            String itemLabel = uiItem.getItemLabel();
            // JSF throws a null pointer exception for null values and labels,
            // which is a serious problem at design-time.
            item = new SelectItem(itemValue, itemLabel == null ? "" : itemLabel, uiItem.getItemDescription(), uiItem.isItemDisabled());
        }
        items.add(item);
    }

    protected void addSelectItems(UISelectItems uiItems, List<SelectItem> items) {

        boolean isRendered = uiItems.isRendered();
        if (!isRendered) {
            items.add(null);
            return;
        }

        Object value = uiItems.getValue();
        if (value instanceof SelectItem) {
            items.add((SelectItem) value);
        } else if (value instanceof Object[]) {
            Object[] array = (Object[]) value;
            for (Object array1 : array) {
                // TODO test - this section is untested
                if (array1 instanceof SelectItemGroup) {
                    resolveAndAddItems((SelectItemGroup) array1, items);
                } else {
                    items.add((SelectItem) array1);
                }
            }
        } else if (value instanceof Collection) {
            Iterator<Object> iter = ((Collection<Object>) value)
                    .iterator();
            Object item;
            while (iter.hasNext()) {
                item = iter.next();
                if (item instanceof SelectItemGroup) {
                    resolveAndAddItems((SelectItemGroup) item, items);
                } else {
                    items.add(new SelectItem(item));
                }
            }
        } else if (value instanceof Map) {
            for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) value).entrySet()) {
                Object label = entry.getKey();
                SelectItem item = new SelectItem(entry.getValue(), label == null ? (String) null : label.toString());
                if (item instanceof SelectItemGroup) {
                    resolveAndAddItems((SelectItemGroup) item, items);
                } else {
                    items.add(item);
                }
            }
        }
    }

    protected void resolveAndAddItems(SelectItemGroup group, List<SelectItem> items) {
        for (SelectItem item : group.getSelectItems()) {
            if (item instanceof SelectItemGroup) {
                resolveAndAddItems((SelectItemGroup) item, items);
            } else {
                items.add(item);
            }
        }
    }
}
