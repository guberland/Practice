from django.http import HttpResponse
from django.shortcuts import render

from .models import Catalog
# Create your views here.

def catalog_list(request):
    catalogs = Catalog.objects.all()
    return render(request,'catalogs/catalog_list.html', {'catalogs': catalogs})


