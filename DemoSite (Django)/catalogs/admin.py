from django.contrib import admin

# Register your models here.

from .models import Catalog,Procedure

class StepInline(admin.StackedInline):
    model = Procedure
    
class CatalogAdmin(admin.ModelAdmin):
    inlines = [StepInline,]



admin.site.register(Catalog,CatalogAdmin)
admin.site.register(Procedure)