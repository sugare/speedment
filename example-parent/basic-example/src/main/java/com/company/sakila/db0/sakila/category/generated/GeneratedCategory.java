package com.company.sakila.db0.sakila.category.generated;

import com.company.sakila.db0.sakila.category.Category;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.field.ComparableField;
import com.speedment.runtime.field.ShortField;
import com.speedment.runtime.field.StringField;
import com.speedment.runtime.typemapper.TypeMapper;
import java.sql.Timestamp;

/**
 * The generated base for the {@link
 * com.company.sakila.db0.sakila.category.Category}-interface representing
 * entities of the {@code category}-table in the database.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedCategory {
    
    /**
     * This Field corresponds to the {@link Category} field that can be obtained
     * using the {@link Category#getCategoryId()} method.
     */
    ShortField<Category, Short> CATEGORY_ID = ShortField.create(
        Identifier.CATEGORY_ID,
        Category::getCategoryId,
        Category::setCategoryId,
        TypeMapper.primitive(),
        true
    );
    /**
     * This Field corresponds to the {@link Category} field that can be obtained
     * using the {@link Category#getName()} method.
     */
    StringField<Category, String> NAME = StringField.create(
        Identifier.NAME,
        Category::getName,
        Category::setName,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Category} field that can be obtained
     * using the {@link Category#getLastUpdate()} method.
     */
    ComparableField<Category, Timestamp, Timestamp> LAST_UPDATE = ComparableField.create(
        Identifier.LAST_UPDATE,
        Category::getLastUpdate,
        Category::setLastUpdate,
        TypeMapper.identity(),
        false
    );
    
    /**
     * Returns the categoryId of this Category. The categoryId field corresponds
     * to the database column db0.sakila.category.category_id.
     * 
     * @return the categoryId of this Category
     */
    short getCategoryId();
    
    /**
     * Returns the name of this Category. The name field corresponds to the
     * database column db0.sakila.category.name.
     * 
     * @return the name of this Category
     */
    String getName();
    
    /**
     * Returns the lastUpdate of this Category. The lastUpdate field corresponds
     * to the database column db0.sakila.category.last_update.
     * 
     * @return the lastUpdate of this Category
     */
    Timestamp getLastUpdate();
    
    /**
     * Sets the categoryId of this Category. The categoryId field corresponds to
     * the database column db0.sakila.category.category_id.
     * 
     * @param categoryId to set of this Category
     * @return           this Category instance
     */
    Category setCategoryId(short categoryId);
    
    /**
     * Sets the name of this Category. The name field corresponds to the
     * database column db0.sakila.category.name.
     * 
     * @param name to set of this Category
     * @return     this Category instance
     */
    Category setName(String name);
    
    /**
     * Sets the lastUpdate of this Category. The lastUpdate field corresponds to
     * the database column db0.sakila.category.last_update.
     * 
     * @param lastUpdate to set of this Category
     * @return           this Category instance
     */
    Category setLastUpdate(Timestamp lastUpdate);
    
    enum Identifier implements ColumnIdentifier<Category> {
        
        CATEGORY_ID ("category_id"),
        NAME        ("name"),
        LAST_UPDATE ("last_update");
        
        private final String columnName;
        private final TableIdentifier<Category> tableIdentifier;
        
        Identifier(String columnName) {
            this.columnName      = columnName;
            this.tableIdentifier = TableIdentifier.of(    getDbmsName(), 
                getSchemaName(), 
                getTableName());
        }
        
        @Override
        public String getDbmsName() {
            return "db0";
        }
        
        @Override
        public String getSchemaName() {
            return "sakila";
        }
        
        @Override
        public String getTableName() {
            return "category";
        }
        
        @Override
        public String getColumnName() {
            return this.columnName;
        }
        
        @Override
        public TableIdentifier<Category> asTableIdentifier() {
            return this.tableIdentifier;
        }
    }
}