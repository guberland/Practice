from django.contrib import admin

# Register your models here.

from .models import Catalog,Procedure

admin.site.register(Catalog)
admin.site.register(Procedure)