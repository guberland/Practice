from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^$', views.catalog_list,name='list'),
    url(r'(?P<catalog_pk>\d+)/(?P<procedure_pk>\d+)/$', views.procedure_detail,name='procedure'),            
    url(r'(?P<pk>\d+)/$', views.catalog_detail,name='detail'),
               ]